package com.lzx.distributed.lock.config;

import com.lzx.distributed.lock.interceptor.AutoIdempotentInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Resource
    private AutoIdempotentInterceptor autoIdempotentInterceptor;

    @Autowired
    public void setAutoIdempotentInterceptor(AutoIdempotentInterceptor autoIdempotentInterceptor) {
        this.autoIdempotentInterceptor = autoIdempotentInterceptor;
    }

    /**
     * 添加拦截器
     */
    @Override

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoIdempotentInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/good/**", "/static/**");

    }

}
