package com.dynamic.data.source.config;

import com.dynamic.data.source.dynamic.DynamicDataSource;
import com.dynamic.data.source.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Bean(name = "master")
    public DataSource masterDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = build(env, "spring.datasource.druid.master.");
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("master");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);
        return ds;
    }

    @Bean(name = "slave")
    public AtomikosDataSourceBean slaveDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = build(env, "spring.datasource.druid.slave.");
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("slave");
        ds.setPoolSize(5);
        ds.setXaProperties(prop);
        return ds;
    }

    @Primary//保证注入的数据源是动态的数据源
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource());
        targetDataSources.put(DataSourceType.SLAVE.name(), slaveDataSource());
        return new DynamicDataSource(masterDataSource(), targetDataSources);
    }

    private Properties build(Environment env, String prefix) {
        Properties prop = new Properties();
        prop.put("url", Objects.requireNonNull(env.getProperty(prefix + "url")));
        prop.put("username", Objects.requireNonNull(env.getProperty(prefix + "username")));
        prop.put("password", Objects.requireNonNull(env.getProperty(prefix + "password")));
        prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
        prop.put("initialSize", Objects.requireNonNull(env.getProperty(prefix + "initialSize", Integer.class)));
        prop.put("maxActive", Objects.requireNonNull(env.getProperty(prefix + "maxActive", Integer.class)));
        prop.put("minIdle", Objects.requireNonNull(env.getProperty(prefix + "minIdle", Integer.class)));
        prop.put("maxWait", Objects.requireNonNull(env.getProperty(prefix + "maxWait", Integer.class)));
        prop.put("poolPreparedStatements", Objects.requireNonNull(env.getProperty(prefix + "poolPreparedStatements", Boolean.class)));
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                Objects.requireNonNull(env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class)));
        prop.put("validationQuery", Objects.requireNonNull(env.getProperty(prefix + "validationQuery")));
        prop.put("validationQueryTimeout", Objects.requireNonNull(env.getProperty(prefix + "validationQueryTimeout", Integer.class)));
        prop.put("testOnBorrow", Objects.requireNonNull(env.getProperty(prefix + "testOnBorrow", Boolean.class)));
        prop.put("testOnReturn", Objects.requireNonNull(env.getProperty(prefix + "testOnReturn", Boolean.class)));
        prop.put("testWhileIdle", Objects.requireNonNull(env.getProperty(prefix + "testWhileIdle", Boolean.class)));
        prop.put("timeBetweenEvictionRunsMillis",
                Objects.requireNonNull(env.getProperty(prefix + "timeBetweenEvictionRunsMillis", Integer.class)));
        prop.put("minEvictableIdleTimeMillis", Objects.requireNonNull(env.getProperty(prefix + "minEvictableIdleTimeMillis", Integer.class)));
        prop.put("filters", Objects.requireNonNull(env.getProperty(prefix + "filters")));

        return prop;
    }

}
