package com.dynamic.data.source.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dynamic.data.source.entity.Course;

import java.util.List;

public interface ICourseService extends IService<Course> {

    List<Course> list();

}
