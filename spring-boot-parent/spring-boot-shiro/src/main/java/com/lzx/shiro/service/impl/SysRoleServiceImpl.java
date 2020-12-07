package com.lzx.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzx.shiro.dao.SysRoleDao;
import com.lzx.shiro.entity.SysRoleEntity;
import com.lzx.shiro.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色业务实现
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    /**
     * 通过用户ID查询角色集合
     * @param  userId 用户ID
     * @return List<SysRoleEntity> 角色名集合
     */
    @Override
    public List<SysRoleEntity> selectSysRoleByUserId(Long userId) {
        return this.baseMapper.selectSysRoleByUserId(userId);
    }

}
