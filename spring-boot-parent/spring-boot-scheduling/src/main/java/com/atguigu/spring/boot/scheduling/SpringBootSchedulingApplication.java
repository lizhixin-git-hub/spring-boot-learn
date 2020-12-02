package com.atguigu.spring.boot.scheduling;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 转载：https://mp.weixin.qq.com/s/f7zzweNREGE10kLGHCIq_g
 * 添加执行定时任务的线程池配置类
 * 添加定时任务示例类
 * 定时任务数据库表设计
 * 添加定时任务实体类
 * 新增定时任务
 *
 * 在spring boot项目中，可以通过@EnableScheduling注解和@Scheduled注解实现定时任务，也可以通过SchedulingConfigurer接口来实现定时任务。但是这两种方式不能动态添加、删除、启动、停止任务。
 * 要实现动态增删启停定时任务功能，比较广泛的做法是集成Quartz框架。但是本人的开发原则是：在满足项目需求的情况下，尽量少的依赖其它框架，避免项目过于臃肿和复杂。
 * 查看spring-context这个jar包中org.springframework.scheduling.ScheduledTaskRegistrar这个类的源代码，发现可以通过改造这个类就能实现动态增删启停定时任务功能。
 */
@SpringBootApplication
@MapperScan("com.atguigu.spring.boot.scheduling.dao")
public class SpringBootSchedulingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSchedulingApplication.class, args);
    }

}
