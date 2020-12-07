package com.lzx.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzx.shiro.entity.SysMenuEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限DAO
 */
@Repository
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    /**
     * 根据角色查询用户权限
     * @param  roleId 角色ID
     * @return List<SysMenuEntity> 权限集合
     */
    List<SysMenuEntity> selectSysMenuByRoleId(Long roleId);

}
