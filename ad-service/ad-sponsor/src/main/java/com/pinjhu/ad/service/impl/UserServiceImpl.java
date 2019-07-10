package com.pinjhu.ad.service.impl;

import com.pinjhu.ad.constant.Constants;
import com.pinjhu.ad.dao.AdUserRepository;
import com.pinjhu.ad.entity.AdUser;
import com.pinjhu.ad.exception.AdException;
import com.pinjhu.ad.service.IUserService;
import com.pinjhu.ad.utils.CommonUtils;
import com.pinjhu.ad.vo.CreateUserRequest;
import com.pinjhu.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final AdUserRepository adUserRepository;

    @Autowired
    public UserServiceImpl(AdUserRepository adUserRepository) {
        this.adUserRepository = adUserRepository;
    }

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {

        if (!request.validate())
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        if (adUserRepository.findByUsername(request.getUserName()) != null)
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);

        AdUser newUser = adUserRepository.save(new AdUser(request.getUserName(),
                         CommonUtils.md5(request.getUserName())));

        return new CreateUserResponse(newUser.getId(), newUser.getUsername(), newUser.getToken(),
                newUser.getCreateTime(), newUser.getUpdateTime());

    }
}
