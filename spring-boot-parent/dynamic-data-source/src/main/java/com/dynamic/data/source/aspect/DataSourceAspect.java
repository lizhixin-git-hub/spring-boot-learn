package com.dynamic.data.source.aspect;

import com.dynamic.data.source.annotation.DataSource;
import com.dynamic.data.source.dynamic.DynamicDataSourceContextHolder;
import com.dynamic.data.source.enums.DataSourceType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 多数据源处理
 *
 * @author DUCHONG
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.dynamic.data.source.annotation.DataSource)")
    public void dsPointCut() {

    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();

        Method method = signature.getMethod();

        DataSource dataSource = method.getAnnotation(DataSource.class);

        if (Objects.isNull(dataSource)) {
            DynamicDataSourceContextHolder.setDateSourceType(DataSourceType.MASTER.name());
        } else {
            DynamicDataSourceContextHolder.setDateSourceType(dataSource.value().name());
        }

        try {
            return point.proceed();
        } finally {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDateSourceType();
        }
    }

}
