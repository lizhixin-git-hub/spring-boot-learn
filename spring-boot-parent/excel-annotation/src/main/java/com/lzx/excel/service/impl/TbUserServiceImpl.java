package com.lzx.excel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzx.excel.dao.TbUserDao;
import com.lzx.excel.entity.TbUser;
import com.lzx.excel.service.ITbUserService;
import org.springframework.stereotype.Service;

@Service("tbUserService")
public class TbUserServiceImpl extends ServiceImpl<TbUserDao, TbUser> implements ITbUserService {

}
