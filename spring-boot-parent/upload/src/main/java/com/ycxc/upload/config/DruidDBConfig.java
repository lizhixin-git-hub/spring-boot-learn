package com.ycxc.upload.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.ycxc.upload.config.properties.DBProperties;
import com.ycxc.upload.config.properties.DruidProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DruidDBConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DruidDBConfig.class);

    private final DBProperties dbProperties;

    private final DruidProperties druidProperties;

    public DruidDBConfig(@Autowired DBProperties dbProperties,@Autowired DruidProperties druidProperties){
        this.dbProperties=dbProperties;
        this.druidProperties=druidProperties;
    }

    @Primary
    @Bean(destroyMethod = "close")
    public DruidDataSource druidDataSource() {
        final DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dbProperties.getUrl());
        datasource.setUsername(dbProperties.getUsername());
        datasource.setPassword(dbProperties.getPassword());
        datasource.setDriverClassName(dbProperties.getDriverClassName());
        datasource.setInitialSize(dbProperties.getInitialSize());
        datasource.setMinIdle(dbProperties.getMinIdle());
        datasource.setMaxActive(dbProperties.getMaxActive());
        datasource.setMaxWait(dbProperties.getMaxWait());
        datasource.setRemoveAbandoned(dbProperties.isRemoveAbandoned());
        datasource.setRemoveAbandonedTimeout(dbProperties.getRemoveAbandonedTimeout());
        datasource.setLogAbandoned(dbProperties.isLogAbandoned());
        datasource.setTimeBetweenEvictionRunsMillis(dbProperties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(dbProperties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(dbProperties.getValidationQuery());
        datasource.setTestOnReturn(dbProperties.isTestOnReturn());
        datasource.setPoolPreparedStatements(dbProperties.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(dbProperties.getMaxPoolPreparedStatementPerConnectionSize());
        datasource.setUseGlobalDataSourceStat(dbProperties.isUseGlobalDataSourceStat());
        try {
            datasource.setFilters(dbProperties.getFilters());
        } catch (SQLException e) {
            LOGGER.info("setFilters Exception ={}", e.getMessage());
        }
        datasource.setConnectionProperties(dbProperties.getConnectionProperties());
        return datasource;
    }

    @Bean
    public PlatformTransactionManager dataSourceTransactionManager(@Autowired DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        //创建ServletRegistrationBean对象
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>();
        //设置servlet
        servletRegistrationBean.setServlet(new StatViewServlet());
        //设置路径映射
        servletRegistrationBean.addUrlMappings(druidProperties.getUrlMapping());
        //设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername",druidProperties.getUserName());
        //设置控制台管理用户密码
        servletRegistrationBean.addInitParameter("loginPassword",druidProperties.getPassword());
        // 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable","false");
        //设置白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //返回ServletRegistrationBean对象
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        //创建过滤器
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        //设置过滤器
        filterRegistrationBean.setFilter(new WebStatFilter());
        //创建map存放参数
        Map<String, String> initParams = new HashMap<>();
        //忽略过滤的形式
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        //设置map参数
        filterRegistrationBean.setInitParameters(initParams);
        //设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //返回过滤器
        return filterRegistrationBean;
    }
}
