package com.lzx.distributed.lock.entity;

import com.lzx.distributed.lock.enums.LikedStatusEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户点赞表
 */
@Entity
public class UserLike implements Serializable {

    //主键id
    @Id
    private Integer id;

    //被点赞的用户的id
    private String likedUserId;

    //点赞的用户的id
    private String likedPostId;

    //点赞的状态.默认未点赞
    private Integer status = LikedStatusEnum.UNLIKE.getCode();

    public UserLike() {
    }

    public UserLike(String likedUserId, String likedPostId, Integer status) {
        this.likedUserId = likedUserId;
        this.likedPostId = likedPostId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLikedUserId() {
        return likedUserId;
    }

    public void setLikedUserId(String likedUserId) {
        this.likedUserId = likedUserId;
    }

    public String getLikedPostId() {
        return likedPostId;
    }

    public void setLikedPostId(String likedPostId) {
        this.likedPostId = likedPostId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
