package com.lzx.distributed.lock.service;

import com.lzx.distributed.lock.entity.UserInfo;

public interface UserService {

    /**
     * 通过id查询用户
     *
     * @param id 用户Id
     * @return 用户
     */
    UserInfo findById(String id);

    /**
     * 更新个人信息
     *
     * @param userInfo 用户信息
     * @return 用户信息
     */
    UserInfo updateInfo(UserInfo userInfo);

}
