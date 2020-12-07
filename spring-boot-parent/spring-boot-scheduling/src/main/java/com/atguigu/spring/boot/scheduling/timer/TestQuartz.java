package com.atguigu.spring.boot.scheduling.timer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * Quartz：这是一个功能比较强大的的调度器，可以让你的程序在指定时间执行，也可以按照某一个频度执行，配置起来稍显复杂。
 */
public class TestQuartz extends QuartzJobBean {

    /**
     * 执行定时任务
     * @param jobExecutionContext 任务执行上下文
     * //@throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("quartz task " + new Date());
    }

}
