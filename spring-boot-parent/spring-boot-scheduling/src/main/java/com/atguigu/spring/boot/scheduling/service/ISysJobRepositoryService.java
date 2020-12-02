package com.atguigu.spring.boot.scheduling.service;

import com.atguigu.spring.boot.scheduling.entity.SysJobPO;
import com.atguigu.spring.boot.scheduling.util.OperationResUtils;

import java.util.List;

public interface ISysJobRepositoryService {

    /**
     * 新增任务
     * @param sysJob 任务信息
     * @return 结果
     */
    OperationResUtils addSysJob(SysJobPO sysJob);

    /**
     * 编辑任务
     * @param sysJob 任务信息
     * @return 结果
     */
    OperationResUtils editSysJob(SysJobPO sysJob);

    /**
     * 编辑任务状态
     * @param sysJob 任务信息
     * @return 结果
     */
    OperationResUtils editSysJobByStatus(SysJobPO sysJob);

    /**
     * 删除任务
     * @param jobId 任务id
     * @return 结果
     */
    OperationResUtils deleteSysJobById(int jobId);

    /**
     * 通过状态获取任务
     * @param status 状态
     * @return 任务列表
     */
    List<SysJobPO>  getSysJobListByStatus(int status);

}
