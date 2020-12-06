package com.dynamic.data.source.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.dynamic.data.source.dynamic.DynamicDataSource;
import com.dynamic.data.source.enums.DataSourceType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * druid 配置多数据源
 */
//@Configuration
public class DruidConfig {

    @Bean("master")
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("slave")
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
    public DataSource slaveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary//保证注入的数据源是动态的数据源
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource());
        targetDataSources.put(DataSourceType.SLAVE.name(), slaveDataSource());
        return new DynamicDataSource(masterDataSource(), targetDataSources);
    }

}
