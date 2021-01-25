package com.lzx.distributed.lock.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

@Entity
@DynamicUpdate
public class UserInfo {

    @Id
    private String id;

    //用户昵称
    private String name;

    //密码
    private String password;

    //token. 改为存储到 Redis 中，不再在数据库中存
    //private String token;

    //性别。0未知 1男 2女
    private Integer sex;

    //电话
    private String tel;

    //邮箱
    private String email;

    //头像
    private String avatar;

    //微信的 openid
    private String openid;

    //qq的 id
    private String qqId;

    //用户角色。0未知
    private Integer role;

    //城市
    private String city;

    //工作经验。0:在校生 1:0年 2:1-2年 3:3-5年 4:5-10年 5:10年+
    private Integer experience;

    //技能
    private String skill;

    //工作日空闲开始时间
    private Time workdayStartTime;

    //工作日空闲结束时间
    private Time workdayEndTime;

    //周末空闲开始时间
    private Time weekendStartTime;

    //周末空闲结束时间
    private Time weekendEndTime;

    //影响力
    private Integer influence;

    //点赞数量
    private Integer likeNum;

    //个人简介
    private String introduce;

    public UserInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Time getWorkdayStartTime() {
        return workdayStartTime;
    }

    public void setWorkdayStartTime(Time workdayStartTime) {
        this.workdayStartTime = workdayStartTime;
    }

    public Time getWorkdayEndTime() {
        return workdayEndTime;
    }

    public void setWorkdayEndTime(Time workdayEndTime) {
        this.workdayEndTime = workdayEndTime;
    }

    public Time getWeekendStartTime() {
        return weekendStartTime;
    }

    public void setWeekendStartTime(Time weekendStartTime) {
        this.weekendStartTime = weekendStartTime;
    }

    public Time getWeekendEndTime() {
        return weekendEndTime;
    }

    public void setWeekendEndTime(Time weekendEndTime) {
        this.weekendEndTime = weekendEndTime;
    }

    public Integer getInfluence() {
        return influence;
    }

    public void setInfluence(Integer influence) {
        this.influence = influence;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
