package com.lzx.distributed.lock.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lzx.distributed.lock.entity.Good;
import com.lzx.distributed.lock.service.IGoodService;
import com.lzx.distributed.lock.utils.RedisLockUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/good")
public class GoodController {

    private IGoodService goodService;

    private RedisLockUtils redisLockUtils;

    @Autowired
    public void setGoodService(IGoodService goodService) {
        this.goodService = goodService;
    }

    @Autowired
    public void setRedisLockUtils(RedisLockUtils redisLockUtils) {
        this.redisLockUtils = redisLockUtils;
    }

    /**
     * 减少商品库存（模拟高并发）
     */
    @RequestMapping("/reduceInventory")
    public void reduceInventory() {
        //客户端ID
        String clientId = UUID.randomUUID().toString().replace("-", "");

        try {
            //获取锁
            if (redisLockUtils.tryLock("1", clientId, 30)) {

                //获取商品信息
                Good good = goodService.getOne(Wrappers.<Good>lambdaQuery()
                        .eq(Good::getId, 1));

                if (good.getStock() > 0) {
                    //将库存减一设置回数据库
                    good.setStock(good.getStock() - 1);
                    goodService.updateById(good);
                    System.out.println("减少商品库存成功，当前库存数为：" + good.getStock());
                    return;
                }
            }
        } finally {
            //释放锁
            redisLockUtils.releaseLock("1", clientId);
        }

        //商品库存不足
        System.out.println("商品库存不足！！！");
    }

}
