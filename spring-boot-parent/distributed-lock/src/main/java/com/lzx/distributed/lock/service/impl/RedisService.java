package com.lzx.distributed.lock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 写入缓存
     * @param key key
     * @param value 值
     * @return 结果
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 写入缓存设置时效时间
     * @param key key
     * @param value 值
     * @return 结果
     */
    public boolean setEx(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 判断缓存中是否有对应的value
     * @param key key
     * @return 是否存在
     */
    public Boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     * @param key key
     * @return 结果
     */
    public Object get(final String key) {
        Object result;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 删除对应的value
     * @param key key
     * @return 结果
     */
    public Boolean remove(final String key) {
        if (exists(key)) {
            return redisTemplate.delete(key);
        }
        return false;

    }

}
