package com.lzx.distributed.lock.service.impl;

import com.lzx.distributed.lock.entity.UserInfo;
import com.lzx.distributed.lock.mappr.UserInfoRepository;
import com.lzx.distributed.lock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserInfoRepository userRepository;

    @Autowired
    public void setUserRepository(UserInfoRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserInfo findById(String id) {
        return userRepository.findUserInfoById(id);
    }

    @Override
    @Transactional
    public UserInfo updateInfo(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

}
