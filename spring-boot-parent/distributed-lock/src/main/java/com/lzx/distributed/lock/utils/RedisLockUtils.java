package com.lzx.distributed.lock.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
public class RedisLockUtils {

    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 该加锁方法仅针对单实例 Redis 可实现分布式加锁
     * 对于 Redis 集群则无法使用
     * <p>
     * 支持重复，线程安全
     *
     * @param lockKey  加锁键
     * @param clientId 加锁客户端唯一标识(采用UUID)
     * @param seconds  锁过期时间
     * @return 是否获取锁
     */
    public Boolean tryLock(String lockKey, String clientId, long seconds) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.set(lockKey.getBytes(StandardCharsets.UTF_8), clientId.getBytes(StandardCharsets.UTF_8), Expiration.seconds(seconds), RedisStringCommands.SetOption.SET_IF_ABSENT));
    }

    /**
     * 与 tryLock 相对应，用作释放锁
     *
     * @param lockKey 加锁键
     * @param clientId 加锁客户端唯一标识(采用UUID)
     * @return 是否释放锁
     */
    public Boolean releaseLock(String lockKey, String clientId) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.eval(RELEASE_LOCK_SCRIPT.getBytes(), ReturnType.BOOLEAN ,1, lockKey.getBytes(StandardCharsets.UTF_8), clientId.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 获取Redis锁的value值
     * @param lockKey 加锁键
     * @return Redis锁的value值(加锁客户端唯一标识)
     */
    public String get(String lockKey) {
            return redisTemplate.execute((RedisCallback<String>) connection -> new String(Objects.requireNonNull(connection.get(lockKey.getBytes())), StandardCharsets.UTF_8));
    }

}
