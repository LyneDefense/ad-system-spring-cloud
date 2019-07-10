package com.pinjhu.ad.service;

import com.pinjhu.ad.exception.AdException;
import com.pinjhu.ad.vo.CreateUserRequest;
import com.pinjhu.ad.vo.CreateUserResponse;

public interface IUserService {

    public CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
