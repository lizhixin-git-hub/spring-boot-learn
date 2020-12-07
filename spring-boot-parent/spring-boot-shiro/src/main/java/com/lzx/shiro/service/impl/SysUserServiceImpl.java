package com.lzx.shiro.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzx.shiro.dao.SysUserDao;
import com.lzx.shiro.entity.SysUserEntity;
import com.lzx.shiro.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * 系统用户业务实现
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {


    /**
     * 根据用户名查询实体
     * @param  username 用户名
     * @return SysUserEntity 用户实体
     */
    @Override
    public SysUserEntity selectUserByName(String username) {
        return this.baseMapper.selectOne(Wrappers.<SysUserEntity>lambdaQuery().eq(SysUserEntity::getUsername,username));
    }

}
