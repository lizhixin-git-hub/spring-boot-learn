package com.lzx.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzx.shiro.entity.SysUserRoleEntity;
import org.springframework.stereotype.Repository;

/**
 * 用户与角色关系DAO
 */
@Repository
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {

}
