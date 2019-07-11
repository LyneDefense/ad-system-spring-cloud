package com.pinjhu.ad.controller;

import com.pinjhu.ad.annotation.IgnoreResponseAdvice;
import com.pinjhu.ad.client.SponsorClient;
import com.pinjhu.ad.client.vo.AdPlan;
import com.pinjhu.ad.client.vo.AdPlanGetRequest;
import com.pinjhu.ad.vo.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class SearchController {

    private final RestTemplate restTemplate;
    private final SponsorClient sponsorClient;

    @Autowired
    public SearchController(RestTemplate restTemplate, SponsorClient sponsorClient) {
        this.restTemplate = restTemplate;
        this.sponsorClient = sponsorClient;
    }

    @SuppressWarnings("all")
    @PostMapping("/getAdplansByRebbon")
    @IgnoreResponseAdvice
    public CommonResponse<List<AdPlan>> getAdPlansByRebbon(@RequestBody AdPlanGetRequest request){

        return restTemplate.postForEntity("http://",request,CommonResponse.class).getBody();
    }

    @IgnoreResponseAdvice
    @PostMapping("/getAdPlans")
    public CommonResponse<List<AdPlan>> getAdPlans(@RequestBody AdPlanGetRequest request){


        return sponsorClient.getAdPlans(request);
    }

}
