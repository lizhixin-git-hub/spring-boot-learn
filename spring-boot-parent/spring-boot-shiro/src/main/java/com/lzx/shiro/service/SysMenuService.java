package com.lzx.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzx.shiro.entity.SysMenuEntity;

import java.util.List;

/**
 * 权限业务接口
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    /**
     * 根据角色查询用户权限0:14
     * @param  roleId 角色ID
     * @return List<SysMenuEntity> 权限集合
     */
    List<SysMenuEntity> selectSysMenuByRoleId(Long roleId);

}
