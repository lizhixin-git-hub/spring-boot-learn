package com.lzx.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzx.shiro.dao.SysUserRoleDao;
import com.lzx.shiro.entity.SysUserRoleEntity;
import com.lzx.shiro.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 *  用户与角色业务实现
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

}
