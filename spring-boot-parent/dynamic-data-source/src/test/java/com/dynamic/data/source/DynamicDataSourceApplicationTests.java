package com.dynamic.data.source;

import com.dynamic.data.source.entity.Course;
import com.dynamic.data.source.entity.Good;
import com.dynamic.data.source.service.ICourseService;
import com.dynamic.data.source.service.IGoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@SpringBootTest
public class DynamicDataSourceApplicationTests {

    private ICourseService courseService;

    private IGoodService goodService;

    @Autowired
    public void setCourseService(ICourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setGoodService(IGoodService goodService) {
        this.goodService = goodService;
    }

    @Test
    void contextLoads() {

        List<Course> courses = courseService.list();

        if(!CollectionUtils.isEmpty(courses)) {
            courses.forEach(System.out::println);
        }

        System.out.println("===========================");

        List<Good> goods = goodService.list();

        if(!CollectionUtils.isEmpty(goods)) {
            goods.forEach(System.out::println);
        }

    }
}
