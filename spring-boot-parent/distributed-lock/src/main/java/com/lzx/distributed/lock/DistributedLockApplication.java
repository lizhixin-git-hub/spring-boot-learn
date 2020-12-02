package com.lzx.distributed.lock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 分布式锁资料
 * https://www.cnblogs.com/williamjie/p/9395659.html
 * https://blog.csdn.net/gejinbiao/article/details/84651173
 * https://blog.csdn.net/thanksm1/article/details/10
 * 线程续费：https://cuishilei.com/redis-lock.html
 * https://github.com/chutianmen/redis-lettuce
 * https://blog.csdn.net/u010963948/article/details/79006572
 *
 */
@SpringBootApplication
@MapperScan("com.lzx.distributed.lock.mappr")
public class DistributedLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedLockApplication.class, args);
    }

}
