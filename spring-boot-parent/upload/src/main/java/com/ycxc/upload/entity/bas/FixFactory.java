package com.ycxc.upload.entity.bas;

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
public class FixFactory extends BaseEntity<FixFactory> {

    private static final long serialVersionUID = 1L;

    @TableId("ent_id")
    private Integer entId;

    /**
     * [ONE:1-一类企业; TWO:2-二类企业; THREE:3-三类企业; NOT:4-非营业性维修户]
     */
    @TableField("unit_type")
    private Integer unitType;

    @TableField("work_type_name")
    private String workTypeName;

    /**
     * [NO:0-否; YES:1-是] 是就只能维修本区域的车辆
     */
    @TableField("is_area_limit")
    private Integer isAreaLimit;

    /**
     * [NO:0-否; YES:1-是] 是就需要指定送检站
     */
    @TableField("is_limit_spot")
    private Integer isLimitSpot;

    @TableField("cert_month_max")
    private Integer certMonthMax;

    @TableField("fix_month_max")
    private Integer fixMonthMax;

    @TableField("cert_issue_num")
    private Integer certIssueNum;

    @TableField("cert_used_num")
    private Integer certUsedNum;


    public Integer getEntId() {
        return entId;
    }

    public void setEntId(Integer entId) {
        this.entId = entId;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public String getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }

    public Integer getIsAreaLimit() {
        return isAreaLimit;
    }

    public void setIsAreaLimit(Integer isAreaLimit) {
        this.isAreaLimit = isAreaLimit;
    }

    public Integer getIsLimitSpot() {
        return isLimitSpot;
    }

    public void setIsLimitSpot(Integer isLimitSpot) {
        this.isLimitSpot = isLimitSpot;
    }

    public Integer getCertMonthMax() {
        return certMonthMax;
    }

    public void setCertMonthMax(Integer certMonthMax) {
        this.certMonthMax = certMonthMax;
    }

    public Integer getFixMonthMax() {
        return fixMonthMax;
    }

    public void setFixMonthMax(Integer fixMonthMax) {
        this.fixMonthMax = fixMonthMax;
    }

    public Integer getCertIssueNum() {
        return certIssueNum;
    }

    public void setCertIssueNum(Integer certIssueNum) {
        this.certIssueNum = certIssueNum;
    }

    public Integer getCertUsedNum() {
        return certUsedNum;
    }

    public void setCertUsedNum(Integer certUsedNum) {
        this.certUsedNum = certUsedNum;
    }

    @Override
    protected Serializable pkVal() {
        return this.entId;
    }

    @Override
    public String toString() {
        return "FixFactory{" +
        "entId=" + entId +
        ", unitType=" + unitType +
        ", workTypeName=" + workTypeName +
        ", isAreaLimit=" + isAreaLimit +
        ", isLimitSpot=" + isLimitSpot +
        ", certMonthMax=" + certMonthMax +
        ", fixMonthMax=" + fixMonthMax +
        ", certIssueNum=" + certIssueNum +
        ", certUsedNum=" + certUsedNum +
        "}";
    }
}
