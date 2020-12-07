package com.ycxc.upload.config;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 委托Druid进行Spring 监控
 */
@Configuration
public class SpringMonitoringConfig {

    @Bean(name = "druid-stat-interceptor")
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    // 非单例
    @Scope("prototype")
    @Bean(name = "druid-stat-pointcut")
    public JdkRegexpMethodPointcut druidStatPointcut() {
        final JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        // 这里是你Controller包的路径
        pointcut.setPatterns("com.ycxc.upload.controller.*", "com.ycxc.upload.mapper.*");
        return pointcut;
    }

    @Bean
    public DefaultPointcutAdvisor druidStatAdvisor(@Qualifier("druid-stat-interceptor") final DruidStatInterceptor druidStatInterceptor,
                                                   @Qualifier("druid-stat-pointcut") final JdkRegexpMethodPointcut druidStatPointcut) {
        final DefaultPointcutAdvisor defaultPointAdvisor = new DefaultPointcutAdvisor();
        defaultPointAdvisor.setPointcut(druidStatPointcut);
        defaultPointAdvisor.setAdvice(druidStatInterceptor);
        return defaultPointAdvisor;
    }

}
