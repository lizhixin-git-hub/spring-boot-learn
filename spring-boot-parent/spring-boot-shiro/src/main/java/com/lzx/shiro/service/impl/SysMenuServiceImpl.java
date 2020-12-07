package com.lzx.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzx.shiro.dao.SysMenuDao;
import com.lzx.shiro.entity.SysMenuEntity;
import com.lzx.shiro.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限业务实现
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    /**
     * 根据角色查询用户权限
     * @param  roleId 角色ID
     * @return List<SysMenuEntity> 权限集合
     */
    @Override
    public List<SysMenuEntity> selectSysMenuByRoleId(Long roleId) {
        return this.baseMapper.selectSysMenuByRoleId(roleId);
    }

}
