package com.atguigu.spring.boot.scheduling.initialize;

import com.atguigu.spring.boot.scheduling.entity.SysJobPO;
import com.atguigu.spring.boot.scheduling.enums.SysJobStatus;
import com.atguigu.spring.boot.scheduling.service.ISysJobRepositoryService;
import com.atguigu.spring.boot.scheduling.task.CronTaskRegistrar;
import com.atguigu.spring.boot.scheduling.task.SchedulingRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 添加实现了CommandLineRunner接口的SysJobRunner类，当spring boot项目启动完成后，加载数据库里状态为正常的定时任务。
 */
public class SysJobRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SysJobRunner.class);

    private ISysJobRepositoryService sysJobRepository;

    private CronTaskRegistrar cronTaskRegistrar;

    @Resource
    public void setSysJobRepository(ISysJobRepositoryService sysJobRepository) {
        this.sysJobRepository = sysJobRepository;
    }

    @Resource
    public void setCronTaskRegistrar(CronTaskRegistrar cronTaskRegistrar) {
        this.cronTaskRegistrar = cronTaskRegistrar;
    }

    @Override
    public void run(String... args) {
        // 初始加载数据库里状态为正常的定时任务
        List<SysJobPO> jobList = sysJobRepository.getSysJobListByStatus(SysJobStatus.NORMAL.ordinal());
        if (!CollectionUtils.isEmpty(jobList)) {
            for (SysJobPO job : jobList) {
                SchedulingRunnable task = new SchedulingRunnable(job.getBeanName(), job.getMethodName(), job.getMethodParams());
                cronTaskRegistrar.addCronTask(task, job.getCronExpression());
            }

            logger.info("定时任务已加载完毕...");
        }
    }

}
