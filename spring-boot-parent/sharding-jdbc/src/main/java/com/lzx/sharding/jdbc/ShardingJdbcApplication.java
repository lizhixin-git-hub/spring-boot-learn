package com.lzx.sharding.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 分库分表的应用：
 * 1）：在数据库设计的时候考虑垂直分库和垂直分表
 * 2）：随着数据库数据量增加，不要马上考虑做水平拆分，首先考虑缓存处理，读写分离，使用索引等方式，
 * 如果这些方式根本不能解决问题了，在考虑做水平分库和水平分表。
 * 分库分表带来的问题：
 * 1）：跨节点连接查询的问题(分页、排序)
 * 2）：多数据源管理问题
 *
 * sharding-jdbc并不是分库分表，主要目的是为了简化分库分表后数据相关的操作：
 * 1）数据分片
 * 2）读写分离
 */
@SpringBootApplication
@MapperScan("com.lzx.sharding.jdbc.mapper")
public class ShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class, args);
    }

}
