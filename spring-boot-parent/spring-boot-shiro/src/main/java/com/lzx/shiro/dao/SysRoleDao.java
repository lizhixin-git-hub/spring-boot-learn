package com.lzx.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzx.shiro.entity.SysRoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色DAO
 */
@Repository
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

    /**
     * 通过用户ID查询角色集合
     *
     * @param userId 用户ID
     * @return List<SysRoleEntity> 角色名集合
     */
    List<SysRoleEntity> selectSysRoleByUserId(Long userId);

}
