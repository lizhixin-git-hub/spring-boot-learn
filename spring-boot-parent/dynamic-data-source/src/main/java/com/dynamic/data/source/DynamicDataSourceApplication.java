package com.dynamic.data.source;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//需排除springboot自动配置类加载，数据源配置类 @Primary 会出现重复依赖问题
//多数据源事务https://www.cnblogs.com/cq-yangzhou/p/10945779.html
//https://github.com/lishuangqi/springboot-mybatis-druid-atomikos/
//https://www.cnblogs.com/lixianguo/p/12522442.html
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.dynamic.data.source.mapper")
public class DynamicDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDataSourceApplication.class, args);
    }

}
