package com.lzx.distributed.lock.service;

import com.lzx.distributed.lock.entity.UserLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LikedService {

    /**
     * 保存点赞记录
     * @param userLike 点赞记录
     * @return 点赞记录
     */
    UserLike save(UserLike userLike);

    /**
     * 批量保存或修改
     * @param list 点赞记录列表
     */
    List<UserLike> saveAll(List<UserLike> list);


    /**
     * 根据被点赞人的id查询点赞列表（即查询都谁给这个人点赞过）
     * @param likedUserId 被点赞人的id
     * @param pageable 当前页信息
     * @return 点赞记录
     */
    Page<UserLike> getLikedListByLikedUserId(String likedUserId, Pageable pageable);

    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     * @param likedPostId 点赞记录id
     * @param pageable 当前页信息
     * @return 点赞记录
     */
    Page<UserLike> getLikedListByLikedPostId(String likedPostId, Pageable pageable);

    /**
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     * @param likedUserId 点赞用户id
     * @param likedPostId 点赞记录id
     * @return 点赞记录
     */
    UserLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId);

    /**
     * 将Redis里的点赞数据存入数据库中
     */
    void transLikedFromRedis2DB();

    /**
     * 将Redis中的点赞数量数据存入数据库
     */
    void transLikedCountFromRedis2DB();

}
