package com.lzx.excel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目中使用自定义注解导出/导入excel,也可以使用easy-poi插件
 */
//https://github.com/allanzhuo/myport.git
//https://mp.weixin.qq.com/s/2_M8WOUXSa7j2N4IXUyWZw
@SpringBootApplication
public class ExcelAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelAnnotationApplication.class, args);
    }

}
