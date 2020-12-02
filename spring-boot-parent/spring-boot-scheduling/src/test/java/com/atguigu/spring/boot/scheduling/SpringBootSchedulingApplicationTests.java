package com.atguigu.spring.boot.scheduling;

import com.atguigu.spring.boot.scheduling.entity.SysJobPO;
import com.atguigu.spring.boot.scheduling.enums.SysJobStatus;
import com.atguigu.spring.boot.scheduling.service.ISysJobRepositoryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
public class SpringBootSchedulingApplicationTests {

    private ISysJobRepositoryService sysJobRepositoryService;

    @Resource
    public void setSysJobRepositoryService(ISysJobRepositoryService sysJobRepositoryService) {
        this.sysJobRepositoryService = sysJobRepositoryService;
    }

    @Test
    void contextLoads() {
        SysJobPO sysJob = new SysJobPO();
        sysJob.setBeanName("demoTask");
        sysJob.setMethodName("taskNoParams");
        sysJob.setCronExpression("0 0/1 * * * ?");
        sysJob.setCreateTime(new Date());
        sysJob.setRemark("执行无参示例任务");
        sysJob.setJobStatus(SysJobStatus.NORMAL.ordinal());
        sysJobRepositoryService.addSysJob(sysJob);
    }

}
