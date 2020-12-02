package com.atguigu.spring.boot.scheduling.controller;

import com.atguigu.spring.boot.scheduling.entity.SysJobPO;
import com.atguigu.spring.boot.scheduling.service.ISysJobRepositoryService;
import com.atguigu.spring.boot.scheduling.util.OperationResUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    public ISysJobRepositoryService sysJobRepositoryService;

    @Resource
    public void setSysJobRepositoryService(ISysJobRepositoryService sysJobRepositoryService) {
        this.sysJobRepositoryService = sysJobRepositoryService;
    }

    /**
     * 新增任务
     * @param sysJob 任务信息
     * @return 结果
     */
    @RequestMapping("/addSysJob")
    public OperationResUtils addSysJob(@RequestBody SysJobPO sysJob){
        sysJob.setCreateTime(new Date());
        return sysJobRepositoryService.addSysJob(sysJob);
    }

    /**
     * 编辑任务
     * @param sysJob 任务信息
     * @return 结果
     */
    @RequestMapping("/editSysJob")
    public OperationResUtils editSysJob(@RequestBody SysJobPO sysJob){
        sysJob.setUpdateTime(new Date());
        return sysJobRepositoryService.editSysJob(sysJob);
    }

    /**
     * 编辑任务状态
     * @param sysJob 任务信息
     * @return 结果
     */
    @RequestMapping("/editSysJobByStatus")
    public OperationResUtils editSysJobByStatus(@RequestBody SysJobPO sysJob){
        return sysJobRepositoryService.editSysJobByStatus(sysJob);
    }

    /**
     * 删除任务
     * @param jobId 任务id
     * @return 结果
     */
    @RequestMapping("/deleteSysJobById")
    public OperationResUtils deleteSysJobById(int jobId){
        return sysJobRepositoryService.deleteSysJobById(jobId);
    }

}
