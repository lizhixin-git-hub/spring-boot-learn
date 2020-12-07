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
public class WorkStation extends BaseEntity<WorkStation> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "work_station_id", type = IdType.AUTO)
    private Integer workStationId;

    @TableField("ent_id")
    private Integer entId;

    @TableField("user_name")
    private String userName;

    @TableField("station_no")
    private Integer stationNo;

    @TableField("reader_config")
    private String readerConfig;

    /**
     * [OK:0-正常; CANCEL:1-注销; STOP:2-停用; LOCK:3-锁定]
     */
    @TableField("status")
    private Integer status;

    @TableField("is_fix_check")
    private Integer isFixCheck;


    public Integer getWorkStationId() {
        return workStationId;
    }

    public void setWorkStationId(Integer workStationId) {
        this.workStationId = workStationId;
    }

    public Integer getEntId() {
        return entId;
    }

    public void setEntId(Integer entId) {
        this.entId = entId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStationNo() {
        return stationNo;
    }

    public void setStationNo(Integer stationNo) {
        this.stationNo = stationNo;
    }

    public String getReaderConfig() {
        return readerConfig;
    }

    public void setReaderConfig(String readerConfig) {
        this.readerConfig = readerConfig;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsFixCheck() {
        return isFixCheck;
    }

    public void setIsFixCheck(Integer isFixCheck) {
        this.isFixCheck = isFixCheck;
    }

    @Override
    protected Serializable pkVal() {
        return this.workStationId;
    }

    @Override
    public String toString() {
        return "WorkStation{" +
        "workStationId=" + workStationId +
        ", entId=" + entId +
        ", userName=" + userName +
        ", stationNo=" + stationNo +
        ", readerConfig=" + readerConfig +
        ", status=" + status +
        ", isFixCheck=" + isFixCheck +
        "}";
    }
}
