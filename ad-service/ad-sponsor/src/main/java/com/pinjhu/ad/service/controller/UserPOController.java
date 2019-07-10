package com.pinjhu.ad.service.controller;

import com.alibaba.fastjson.JSON;
import com.pinjhu.ad.exception.AdException;
import com.pinjhu.ad.service.IUserService;
import com.pinjhu.ad.vo.CreateUserRequest;
import com.pinjhu.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserPOController {

    private final IUserService userService;

    @Autowired
    public UserPOController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create/user")
    public CreateUserResponse createUser
            (@RequestBody CreateUserRequest request) throws AdException {

        log.info("ad-sponser:createUser -> {}", JSON.toJSONString(request));

        return userService.createUser(request);
    }
}
