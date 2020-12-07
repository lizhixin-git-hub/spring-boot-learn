package com.ycxc.upload.entity.bus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ycxc.upload.entity.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hack2003
 * @since 2019-07-26
 */
public class EntVideo extends BaseEntity<EntVideo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ent_video_id", type = IdType.AUTO)
    private Integer entVideoId;

    @TableField("ent_id")
    private Integer entId;

    @TableField("out_ip")
    private String outIp;

    @TableField("out_port")
    private String outPort;

    @TableField("user_name")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("channel_num")
    private Integer channelNum;

    @TableField("local_ip")
    private String localIp;

    /**
     * [NET:1-外网; LOCAL:2-内网]
     */
    @TableField("net_type")
    private Integer netType;

    /**
     * [OLD:1-旧; NEW:2-新]
     */
    @TableField("device_type")
    private Integer deviceType;

    /**
     * [NO:0-否; YES:1-是]
     */
    @TableField("is_encrypt")
    private Integer isEncrypt;

    @TableField("camera_name")
    private String cameraName;

    @TableField("is_fix_check")
    private Integer isFixCheck;

    /**
     * 插件类型 0-java 1-C#
     */
    @TableField("plug_type")
    private Integer plugType;


    public Integer getEntVideoId() {
        return entVideoId;
    }

    public void setEntVideoId(Integer entVideoId) {
        this.entVideoId = entVideoId;
    }

    public Integer getEntId() {
        return entId;
    }

    public void setEntId(Integer entId) {
        this.entId = entId;
    }

    public String getOutIp() {
        return outIp;
    }

    public void setOutIp(String outIp) {
        this.outIp = outIp;
    }

    public String getOutPort() {
        return outPort;
    }

    public void setOutPort(String outPort) {
        this.outPort = outPort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(Integer channelNum) {
        this.channelNum = channelNum;
    }

    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    public Integer getNetType() {
        return netType;
    }

    public void setNetType(Integer netType) {
        this.netType = netType;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getIsEncrypt() {
        return isEncrypt;
    }

    public void setIsEncrypt(Integer isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public Integer getIsFixCheck() {
        return isFixCheck;
    }

    public void setIsFixCheck(Integer isFixCheck) {
        this.isFixCheck = isFixCheck;
    }

    public Integer getPlugType() {
        return plugType;
    }

    public void setPlugType(Integer plugType) {
        this.plugType = plugType;
    }

    @Override
    protected Serializable pkVal() {
        return this.entVideoId;
    }

    @Override
    public String toString() {
        return "EntVideo{" +
        "entVideoId=" + entVideoId +
        ", entId=" + entId +
        ", outIp=" + outIp +
        ", outPort=" + outPort +
        ", userName=" + userName +
        ", password=" + password +
        ", channelNum=" + channelNum +
        ", localIp=" + localIp +
        ", netType=" + netType +
        ", deviceType=" + deviceType +
        ", isEncrypt=" + isEncrypt +
        ", cameraName=" + cameraName +
        ", isFixCheck=" + isFixCheck +
        ", plugType=" + plugType +
        "}";
    }
}
