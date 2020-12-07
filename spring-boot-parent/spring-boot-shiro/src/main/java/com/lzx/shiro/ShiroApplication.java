package com.lzx.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//https://gitee.com/liselotte/spring-boot-shiro-demo
//https://github.com/xuyulong2017/my-java-demo
//https://mp.weixin.qq.com/s/aMIvSV4FyqcbY5ygciSa1Q
//扫描DAO
@SpringBootApplication
@MapperScan(basePackages = {"com.lzx.shiro.dao"})
public class ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }

}
