package com.lzx.distributed.lock.service;

import com.lzx.distributed.lock.dto.LikedCountDTO;
import com.lzx.distributed.lock.entity.UserLike;

import java.util.List;

/**
 * 用 Redis 存储两种数据，一种是记录点赞人、被点赞人、点赞状态的数据，另一种是每个用户被点赞了多少次，做个简单的计数。
 *
 * 由于需要记录点赞人和被点赞人，还有点赞状态（点赞、取消点赞），还要固定时间间隔取出 Redis 中所有点赞数据，分析了下 Redis 数据格式中 Hash 最合适。
 *
 * 因为 Hash 里的数据都是存在一个键里，可以通过这个键很方便的把所有的点赞数据都取出。这个键里面的数据还可以存成键值对的形式，方便存入点赞人、被点赞人和点赞状态。
 *
 * 设点赞人的 id 为 likedPostId，被点赞人的 id 为 likedUserId ，点赞时状态为 1，取消点赞状态为 0。将点赞人 id 和被点赞人 id 作为键，两个 id 中间用 :: 隔开，点赞状态作为值。
 *
 * 所以如果用户点赞，存储的键为：likedUserId::likedPostId，对应的值为 1 。取消点赞，存储的键为：likedUserId::likedPostId，对应的值为 0 。取数据时把键用 :: 切开就得到了两个id，也很方便。
 *
 * 在定时任务中直接调用 LikedService 封装的方法完成数据同步。
 *
 * 以上就是点赞功能的设计与实现，不足之处还请各位大佬多多指教。
 *
 * 另外，点赞/取消点赞 跟 点赞数 +1/ -1 应该保证是原子操作 , 不然出现并发问题就会有两条重复的点赞记录 , 所以要给整个原子操作加锁 . 同时需要在Spring Boot 的系统关闭钩子函数中补充同步redis中点赞数据到mysql中的过程 . 不然有可能出现距离上一次同步1小时59分的时候服务器更新 , 把整整两小时的点赞数据都给清空了 . 如果点赞设计到比较重要活动业务的话这就很尴尬了
 */
public interface RedisService {

    /**
     * 点赞。状态为1
     * @param likedUserId 用户Id
     * @param likedPostId 点赞id
     */
    void saveLiked2Redis(String likedUserId, String likedPostId);

    /**
     * 取消点赞。将状态改变为0
     * @param likedUserId 用户Id
     * @param likedPostId 点赞id
     */
    void unlikeFromRedis(String likedUserId, String likedPostId);

    /**
     * 从Redis中删除一条点赞数据
     * @param likedUserId 用户
     * @param likedPostId 点赞id
     */
    void deleteLikedFromRedis(String likedUserId, String likedPostId);

    /**
     * 该用户的点赞数加1
     * @param likedUserId 用户Id
     */
    void incrementLikedCount(String likedUserId);

    /**
     * 该用户的点赞数减1
     * @param likedUserId 用户id
     */
    void decrementLikedCount(String likedUserId);

    /**
     * 获取Redis中存储的所有点赞数据
     * @return Redis中存储的所有点赞数据
     */
    List<UserLike> getLikedDataFromRedis();

    /**
     * 获取Redis中存储的所有点赞数量
     * @return Redis中存储的所有点赞数量
     */
    List<LikedCountDTO> getLikedCountFromRedis();

}
