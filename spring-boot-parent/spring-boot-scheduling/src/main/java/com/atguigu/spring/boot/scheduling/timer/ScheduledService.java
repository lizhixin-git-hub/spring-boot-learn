package com.atguigu.spring.boot.scheduling.timer;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * Spring Task：Spring3.0以后自带的task，可以将它看成一个轻量级的Quartz，而且使用起来比Quartz简单许多
 * 需在主类添加@EnableScheduling
 */
@Component
@Async
public class ScheduledService {

    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled() {
        System.out.println("=====>>>>>使用cron  {}" + System.currentTimeMillis());
    }

    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        System.out.println("=====>>>>>使用fixedRate{}" + System.currentTimeMillis());
    }

    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        System.out.println("=====>>>>>fixedDelay{}" + System.currentTimeMillis());
    }

    /**
     * 定时任务
     */
    @Component
    static class SchedulingConfigurer implements org.springframework.scheduling.annotation.SchedulingConfigurer {
        /**
         * 定时从警视通获取备案企业
         *
         * @param taskRegistrar 任务注册器
         */
        @Override
        public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
            String corn = "0/5 * * * * *";

            //执行定时任务
            taskRegistrar.addTriggerTask(() -> System.out.println("开始执行定时任务"),
                    // 2.设置执行周期(Trigger)
                    triggerContext -> {
                        //定时调用警视通接口时间cron表达式为空
                        /*if (Objects.isNull(corn)) {
                            return null;
                        }*/

                        //创建定时器
                        return new CronTrigger(corn).nextExecutionTime(triggerContext);
                    }
            );
        }
    }
}
