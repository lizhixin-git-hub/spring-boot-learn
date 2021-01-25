package com.lzx.distributed.lock.config;

import com.lzx.distributed.lock.task.LikeTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 本文基于 SpringCloud, 用户发起点赞、取消点赞后先存入 Redis 中，再每隔两小时从 Redis读取点赞数据写入数据库中做持久化存储。
 *
 * 点赞功能在很多系统中都有，但别看功能小，想要做好需要考虑的东西还挺多的。
 *
 * 点赞、取消点赞是高频次的操作，若每次都读写数据库，大量的操作会影响数据库性能，所以需要做缓存。
 *
 * 至于多久从 Redis 取一次数据存到数据库中，根据项目的实际情况定吧，我是暂时设了两个小时。
 *
 * 项目需求需要查看都谁点赞了，所以要存储每个点赞的点赞人、被点赞人，不能简单的做计数。
 *
 * 文章分四部分介绍：
 *
 * Redis 缓存设计及实现
 * 数据库设计
 * 数据库操作
 * 开启定时任务持久化存储到数据库
 *
 * 使用redis实现点赞
 * https://juejin.cn/post/6844903703690870798
 * https://github.com/cachecats/coderiver/tree/master/java/original
 */
@Configuration
public class QuartzConfig {

    private static final String LIKE_TASK_IDENTITY = "LikeTaskQuartz";

    @Bean
    public JobDetail quartzDetail() {
        return JobBuilder.newJob(LikeTask.class).withIdentity(LIKE_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public Trigger quartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                //.withIntervalInSeconds(10)  //设置时间周期单位秒
                .withIntervalInHours(2)  //两个小时执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(LIKE_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }

}
