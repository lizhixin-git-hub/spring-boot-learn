package com.lzx.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzx.shiro.dao.SysRoleMenuDao;
import com.lzx.shiro.entity.SysRoleMenuEntity;
import com.lzx.shiro.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色与权限业务实现
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {
}
