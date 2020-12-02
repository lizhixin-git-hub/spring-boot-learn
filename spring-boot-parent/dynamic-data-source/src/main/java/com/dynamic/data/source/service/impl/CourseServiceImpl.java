package com.dynamic.data.source.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dynamic.data.source.annotation.DataSource;
import com.dynamic.data.source.entity.Course;
import com.dynamic.data.source.enums.DataSourceType;
import com.dynamic.data.source.mapper.CourseMapper;
import com.dynamic.data.source.service.ICourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @DataSource(DataSourceType.SLAVE)
    public List<Course> list() {
        return baseMapper.selectList(Wrappers.lambdaQuery());
    }
    
}
