package com.ycxc.upload.config;

import com.ycxc.upload.config.properties.AsyncTaskExecutorProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步任务线程池配置
 */
@EnableAsync
@Configuration
public class AsyncTaskExecutorConfig {

    private final AsyncTaskExecutorProperties asyncTaskExecutorProperties;

    public AsyncTaskExecutorConfig(@Autowired AsyncTaskExecutorProperties asyncTaskExecutorProperties){
        this.asyncTaskExecutorProperties = asyncTaskExecutorProperties;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTaskExecutorConfig.class);

    @Bean(value = "asyncTaskThreadPool")
    public Executor getAsyncExecutor() {
        //创建线程池对象
        ThreadPoolTaskExecutor asyncTaskThreadPool = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量
        asyncTaskThreadPool.setCorePoolSize(asyncTaskExecutorProperties.getCorePoolSize());
        //线程池维护线程的最大数量
        asyncTaskThreadPool.setMaxPoolSize(asyncTaskExecutorProperties.getMaxPoolSize());
        //线程池维护线程所允许的空闲时间
        asyncTaskThreadPool.setKeepAliveSeconds(asyncTaskExecutorProperties.getKeepAliveSeconds());
        //线程池所使用的缓冲队列
        asyncTaskThreadPool.setQueueCapacity(asyncTaskExecutorProperties.getQueueCapacity());
        //配置线程池中的线程的名称前缀
        asyncTaskThreadPool.setThreadNamePrefix(asyncTaskExecutorProperties.getThreadNamePrefix());
        // 等待所有任务结束后再关闭线程池
        asyncTaskThreadPool.setWaitForTasksToCompleteOnShutdown(asyncTaskExecutorProperties.getWaitForTasksToCompleteOnShutdown());
        //自定义任务处理策略,阻塞队列
        asyncTaskThreadPool.setRejectedExecutionHandler((runnable,executor) -> {
            if (!executor.isShutdown()) {
                try {
                    LOGGER.info("start get queue");
                    executor.getQueue().put(runnable);
                    LOGGER.info("end get queue");
                } catch (InterruptedException e) {
                    LOGGER.error(e.toString(), e);
                    Thread.currentThread().interrupt();
                }
            }
        });
        //初始化
        asyncTaskThreadPool.initialize();
        //返回异步线程池
        return asyncTaskThreadPool;
    }

}
