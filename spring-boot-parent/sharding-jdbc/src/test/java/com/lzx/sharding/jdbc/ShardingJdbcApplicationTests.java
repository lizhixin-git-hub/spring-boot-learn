package com.lzx.sharding.jdbc;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lzx.sharding.jdbc.entity.Course;
import com.lzx.sharding.jdbc.entity.Udict;
import com.lzx.sharding.jdbc.entity.User;
import com.lzx.sharding.jdbc.mapper.CourseMapper;
import com.lzx.sharding.jdbc.mapper.UdictMapper;
import com.lzx.sharding.jdbc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShardingJdbcApplicationTests {

    private CourseMapper courseMapper;

    private UserMapper userMapper;

    private UdictMapper udictMapper;

    @Autowired
    void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Autowired
    void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    void setUdictMapper(UdictMapper udictMapper) {
        this.udictMapper = udictMapper;
    }

    @Test
    void addCourse() {
        for (int i = 0; i <= 20; i++) {
            Course course = new Course();
            course.setCname("java" + i);
            course.setUserId(100L + i);
            course.setCstatus("Normal" + i);
            courseMapper.insert(course);
        }
    }

    @Test
    void findCourse() {
        Course course = courseMapper.selectOne(Wrappers.<Course>lambdaQuery()
                .eq(Course::getCid, 535964837486788608L));
        System.out.println(course);
    }

    @Test
    void findCourseList() {
        List<Course> course = courseMapper.selectList(Wrappers.<Course>lambdaQuery()
                .eq(Course::getCname, "java0")
                .or()
                .eq(Course::getCname, "java19"));
        System.out.println(course);
    }

    @Test
    void addUserDb() {
        User user = new User();
        user.setUsername("lucy");
        user.setUstatus("a");
        userMapper.insert(user);
    }

    @Test
    void findUserDb() {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUserId, 536311354668089345L));
        System.out.println(user);
    }

    //公共表
    //1、存储固定数据的表，表数据很少发生变化，查询时候经常进行关联
    //2、在每个数据库中创建出相同结构的公共表
    @Test
    void addDict() {
        Udict udict = new Udict();
        udict.setUstatus("a");
        udict.setUvalue("已启用");
        udictMapper.insert(udict);
    }

    @Test
    void deleteDict() {
        udictMapper.delete(Wrappers.<Udict>lambdaQuery()
                .eq(Udict::getDictid, 536318255799730177L));
    }
}
