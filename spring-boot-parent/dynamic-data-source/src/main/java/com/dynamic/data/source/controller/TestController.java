package com.dynamic.data.source.controller;

import com.dynamic.data.source.entity.Course;
import com.dynamic.data.source.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private ICourseService courseService;

    @Autowired
    public void setCourseService(ICourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/list")
    public List<Course> list() {
        return courseService.list();
    }

}
