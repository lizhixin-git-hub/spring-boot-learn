package com.dynamic.data.source.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dynamic.data.source.annotation.DataSource;
import com.dynamic.data.source.entity.Good;
import com.dynamic.data.source.enums.DataSourceType;
import com.dynamic.data.source.mapper.GoodMapper;
import com.dynamic.data.source.service.IGoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements IGoodService {

    @DataSource(DataSourceType.MASTER)
    public List<Good> list() {
        return baseMapper.selectList(Wrappers.lambdaQuery());
    }

}
