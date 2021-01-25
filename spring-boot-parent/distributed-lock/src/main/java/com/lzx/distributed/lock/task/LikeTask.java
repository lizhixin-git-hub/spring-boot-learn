package com.lzx.distributed.lock.task;

import com.lzx.distributed.lock.service.LikedService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 点赞的定时任务
 */
public class LikeTask extends QuartzJobBean {

    private LikedService likedService;

    @Autowired
    public void setLikedService(LikedService likedService) {
        this.likedService = likedService;
    }

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("LikeTask-------- " + sdf.format(new Date()));

        //将 Redis 里的点赞信息同步到数据库里
        likedService.transLikedFromRedis2DB();
        likedService.transLikedCountFromRedis2DB();
    }
}
