package com.pinjhu.ad.service;

import com.pinjhu.ad.entity.AdPlan;
import com.pinjhu.ad.exception.AdException;
import com.pinjhu.ad.vo.AdPlanGetRequest;
import com.pinjhu.ad.vo.AdPlanRequest;
import com.pinjhu.ad.vo.AdPlanResponse;

import java.util.List;

public interface IAdPlanService {

    /*
        创建推广计划
     */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /*
        更新推广计划
     */
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /*
        删除推广计划
     */
    void deleteAdPlan(AdPlanRequest request) throws AdException;

    /*
        获取推广计划
     */
    List<AdPlan> getAdPlans(AdPlanGetRequest getRequest) throws AdException;
}
