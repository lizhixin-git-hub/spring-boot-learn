package com.lzx.distributed.lock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzx.distributed.lock.entity.Good;
import com.lzx.distributed.lock.mappr.GoodMapper;
import com.lzx.distributed.lock.service.IGoodService;
import org.springframework.stereotype.Service;

@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements IGoodService {

}
