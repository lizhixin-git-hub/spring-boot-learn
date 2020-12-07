package com.lzx.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzx.shiro.entity.SysUserEntity;

/**
 *  系统用户业务接口
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 根据用户名查询实体
     * @param  username 用户名
     * @return SysUserEntity 用户实体
     */
    SysUserEntity selectUserByName(String username);

}
