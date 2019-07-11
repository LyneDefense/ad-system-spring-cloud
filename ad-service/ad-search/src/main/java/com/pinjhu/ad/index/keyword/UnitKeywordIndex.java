package com.pinjhu.ad.index.keyword;

import com.pinjhu.ad.index.IndexAware;
import com.pinjhu.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
@Slf4j
public class UnitKeywordIndex implements IndexAware<String,Set<Long>> {

    //通过keyword 得到 unitid的map
    private static Map<String,Set<Long>> keywordUnitMap;
    //通过unitid 得到keyword的map
    private static Map<Long,Set<String>> unitKeywordMap;

    static{
        keywordUnitMap = new ConcurrentHashMap<>();
        unitKeywordMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {

        if(key.isEmpty())
            return Collections.emptySet();

        Set<Long> result = keywordUnitMap.get(key);

        if(result.isEmpty())
            return Collections.emptySet();

        return result;
    }

    @Override
    public void add(String key, Set<Long> value) {

        log.info("UnitKeywordIndex, before add: {}", unitKeywordMap);

        Set<Long> unitIds = CommonUtils.getOrCreate(key,keywordUnitMap,ConcurrentSkipListSet::new);
        unitIds.addAll(value);

        for(Long id : value){
            Set<String> keywords = CommonUtils.getOrCreate(id,unitKeywordMap,ConcurrentSkipListSet::new);
            keywords.add(key);
        }

        log.info("UnitKeywordIndex, after add: {}", unitKeywordMap);

    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("keyword index can not support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("UnitKeywordIndex, before delete: {}", unitKeywordMap);

        Set<Long> unitIds = CommonUtils.getOrCreate(key,keywordUnitMap,ConcurrentSkipListSet::new);
        unitIds.removeAll(value);

        for(Long unitId : value){
            Set<String> keywords = CommonUtils.getOrCreate(unitId,unitKeywordMap,ConcurrentSkipListSet::new);
            keywords.remove(key);
        }

        log.info("UnitKeywordIndex, after delete: {}", unitKeywordMap);
    }

    public boolean match(Long unitId,Set<String> keywords){

        if(unitKeywordMap.containsKey(unitId) && CollectionUtils.isEmpty(unitKeywordMap.get(unitId))){

            Set<String> unitKeywords = unitKeywordMap.get(unitId);
            //如果keywords 是 unitKeywords的子集，返回true
            return CollectionUtils.isSubCollection(keywords,unitKeywords);
        }

        return false;
    }
}
