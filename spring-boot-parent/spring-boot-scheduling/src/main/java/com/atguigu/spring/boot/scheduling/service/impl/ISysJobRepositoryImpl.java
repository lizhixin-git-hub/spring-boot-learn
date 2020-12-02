package com.atguigu.spring.boot.scheduling.service.impl;

import com.atguigu.spring.boot.scheduling.dao.SysJobRepository;
import com.atguigu.spring.boot.scheduling.entity.SysJobPO;
import com.atguigu.spring.boot.scheduling.enums.SysJobStatus;
import com.atguigu.spring.boot.scheduling.service.ISysJobRepositoryService;
import com.atguigu.spring.boot.scheduling.task.CronTaskRegistrar;
import com.atguigu.spring.boot.scheduling.task.SchedulingRunnable;
import com.atguigu.spring.boot.scheduling.util.OperationResUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ISysJobRepositoryImpl implements ISysJobRepositoryService {

    private CronTaskRegistrar cronTaskRegistrar;

    private SysJobRepository sysJobRepository;

    @Resource
    public void setCronTaskRegistrar(CronTaskRegistrar cronTaskRegistrar) {
        this.cronTaskRegistrar = cronTaskRegistrar;
    }

    @Resource
    public void setSysJobRepository(SysJobRepository sysJobRepository) {
        this.sysJobRepository = sysJobRepository;
    }

    /**
     * 新增任务
     * @param sysJob 任务信息
     * @return 结果
     */
    @Override
    public OperationResUtils addSysJob(SysJobPO sysJob) {
        boolean success = sysJobRepository.insert(sysJob) > 0;
        if (!success)
            return OperationResUtils.fail("新增失败");
        else {
            if (sysJob.getJobStatus().equals(SysJobStatus.NORMAL.ordinal())) {
                SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
                cronTaskRegistrar.addCronTask(task, sysJob.getCronExpression());
            }
        }

        return OperationResUtils.success();
    }

    /**
     * 编辑任务，修改定时任务，先移除原来的任务，再启动新任务
     * @param sysJob 任务信息
     * @return 结果
     */
    @Override
    public OperationResUtils editSysJob(SysJobPO sysJob){
        SysJobPO existedSysJob = existedSysJob(sysJob.getJobId());

        boolean success = sysJobRepository.updateById(sysJob) > 0;
        if (!success)
            return OperationResUtils.fail("编辑失败");
        else {
            //先移除再添加
            if (existedSysJob.getJobStatus().equals(SysJobStatus.NORMAL.ordinal())) {
                SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
                cronTaskRegistrar.removeCronTask(task);
            }

            if (sysJob.getJobStatus().equals(SysJobStatus.NORMAL.ordinal())) {
                SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
                cronTaskRegistrar.addCronTask(task, sysJob.getCronExpression());
            }
        }

        return OperationResUtils.success();
    }

    /**
     * 删除任务
     * @param jobId 任务id
     * @return 结果
     */
    @Override
    public OperationResUtils deleteSysJobById(int jobId){
        SysJobPO existedSysJob = existedSysJob(jobId);

        SysJobPO sysJobPO = new SysJobPO();
        sysJobPO.setJobId(jobId);
        sysJobPO.setJobStatus(SysJobStatus.SUSPEND.ordinal());
        boolean success = sysJobRepository.updateById(sysJobPO) > 0;
        if (!success)
            return OperationResUtils.fail("删除失败");
        else{
            if (existedSysJob.getJobStatus().equals(SysJobStatus.NORMAL.ordinal())) {
                SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
                cronTaskRegistrar.removeCronTask(task);
            }
        }

        return OperationResUtils.success();
    }

    /**
     * 编辑任务状态
     * @param sysJob 任务信息
     * @return 结果
     */
    @Override
    public OperationResUtils editSysJobByStatus(SysJobPO sysJob){
        SysJobPO existedSysJob = existedSysJob(sysJob.getJobId());
        SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
        if (existedSysJob.getJobStatus().equals(SysJobStatus.NORMAL.ordinal())) {
            cronTaskRegistrar.addCronTask(task, existedSysJob.getCronExpression());
        } else {
            cronTaskRegistrar.removeCronTask(task);
        }
        return OperationResUtils.success();
    }

    @Override
    public List<SysJobPO> getSysJobListByStatus(int status) {
        LambdaQueryWrapper<SysJobPO> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(SysJobPO::getJobStatus, status);
        return sysJobRepository.selectList(lambdaQueryWrapper);
    }

    /**
     * 获取存在任务信息
     * @param jobId 任务ID
     * @return 存在任务信息
     */
    private SysJobPO existedSysJob(int jobId){
        return sysJobRepository.selectById(jobId);
    }

}
