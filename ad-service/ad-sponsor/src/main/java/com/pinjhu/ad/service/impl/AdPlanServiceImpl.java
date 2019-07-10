package com.pinjhu.ad.service.impl;

import com.pinjhu.ad.constant.CommonStatus;
import com.pinjhu.ad.constant.Constants;
import com.pinjhu.ad.dao.AdPlanRepository;
import com.pinjhu.ad.dao.AdUserRepository;
import com.pinjhu.ad.entity.AdPlan;
import com.pinjhu.ad.entity.AdUser;
import com.pinjhu.ad.exception.AdException;
import com.pinjhu.ad.service.IAdPlanService;
import com.pinjhu.ad.utils.CommonUtils;
import com.pinjhu.ad.vo.AdPlanGetRequest;
import com.pinjhu.ad.vo.AdPlanRequest;
import com.pinjhu.ad.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdPlanServiceImpl implements IAdPlanService {


    private final AdPlanRepository adPlanRepository;
    private final AdUserRepository adUserRepository;

    @Autowired
    public AdPlanServiceImpl(AdPlanRepository adPlanRepository, AdUserRepository adUserRepository) {
        this.adPlanRepository = adPlanRepository;
        this.adUserRepository = adUserRepository;
    }

    @Override
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {

        if (!request.createValidate())
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);

        //确保关联的user是存在的
        Optional<AdUser> adUser = adUserRepository.findById(request.getId());
        if(!adUser.isPresent())
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);


        AdPlan oldAdPlan = adPlanRepository.findByUserIdAndPlanName(request.getUserId(),request.getPlanName());
        if (oldAdPlan != null)
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);

        adPlanRepository.save(
                new AdPlan(request.getUserId(),request.getPlanName()
                ,CommonUtils.parseStringDate(request.getStartTime())
                ,CommonUtils.parseStringDate(request.getEndTime()))
        );

        return new AdPlanResponse(request.getUserId(),request.getPlanName());

    }

    @Override
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if (!request.updateValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = adPlanRepository.findByIdAndUserId(
                request.getId(), request.getUserId()
        );
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        if (request.getPlanName() != null) {
            plan.setPlanName(request.getPlanName());
        }
        if (request.getStartTime() != null) {
            plan.setStartDate(
                    CommonUtils.parseStringDate(request.getStartTime())
            );
        }
        if (request.getEndTime() != null) {
            plan.setEndDate(
                    CommonUtils.parseStringDate(request.getEndTime())
            );
        }

        plan.setUpdateTime(new Date());
        plan = adPlanRepository.save(plan);

        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }

    @Override
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if (!request.deleteValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = adPlanRepository.findByIdAndUserId(
                request.getId(), request.getUserId()
        );
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        adPlanRepository.save(plan);
    }

    @Override
    public List<AdPlan> getAdPlans(AdPlanGetRequest getRequest) throws AdException {

        if(!getRequest.validate())
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);

        return adPlanRepository.findAllByIdInAndUserId(getRequest.getIds(),getRequest.getUserId());
    }
}
