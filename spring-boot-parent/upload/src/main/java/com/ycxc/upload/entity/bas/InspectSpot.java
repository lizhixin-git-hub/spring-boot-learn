package com.ycxc.upload.entity.bas;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ycxc.upload.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author hack2003
 * @since 2019-07-26
 */
public class InspectSpot extends BaseEntity<InspectSpot> {

    private static final long serialVersionUID = 1L;

    @TableId("ent_id")
    private Integer entId;

    /**
     * 企业类别
     */
    @TableField("is_fix_inspect")
    private Integer isFixInspect;

    /**
     * 企业类别
     */
    @TableField("is_grade")
    private Integer isGrade;

    /**
     * [A:1-A级站; B:2-B级站; C:3-C级站]
     */
    @TableField("spot_level")
    private Integer spotLevel;

    @TableField("measure_certificate")
    private String measureCertificate;

    @TableField("issue_date")
    private Date issueDate;

    @TableField("valid_date")
    private Date validDate;

    @TableField("issue_unit")
    private String issueUnit;

    @TableField("person_num")
    private Integer personNum;

    @TableField("equipment_num")
    private Integer equipmentNum;

    @TableField("area_total")
    private String areaTotal;

    @TableField("area_inspect")
    private String areaInspect;

    @TableField("area_assistant")
    private String areaAssistant;

    @TableField("area_parking")
    private String areaParking;

    @TableField("area_trial_run")
    private String areaTrialRun;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private Integer createBy;


    public Integer getEntId() {
        return entId;
    }

    public void setEntId(Integer entId) {
        this.entId = entId;
    }

    public Integer getIsFixInspect() {
        return isFixInspect;
    }

    public void setIsFixInspect(Integer isFixInspect) {
        this.isFixInspect = isFixInspect;
    }

    public Integer getIsGrade() {
        return isGrade;
    }

    public void setIsGrade(Integer isGrade) {
        this.isGrade = isGrade;
    }

    public Integer getSpotLevel() {
        return spotLevel;
    }

    public void setSpotLevel(Integer spotLevel) {
        this.spotLevel = spotLevel;
    }

    public String getMeasureCertificate() {
        return measureCertificate;
    }

    public void setMeasureCertificate(String measureCertificate) {
        this.measureCertificate = measureCertificate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getIssueUnit() {
        return issueUnit;
    }

    public void setIssueUnit(String issueUnit) {
        this.issueUnit = issueUnit;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public Integer getEquipmentNum() {
        return equipmentNum;
    }

    public void setEquipmentNum(Integer equipmentNum) {
        this.equipmentNum = equipmentNum;
    }

    public String getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(String areaTotal) {
        this.areaTotal = areaTotal;
    }

    public String getAreaInspect() {
        return areaInspect;
    }

    public void setAreaInspect(String areaInspect) {
        this.areaInspect = areaInspect;
    }

    public String getAreaAssistant() {
        return areaAssistant;
    }

    public void setAreaAssistant(String areaAssistant) {
        this.areaAssistant = areaAssistant;
    }

    public String getAreaParking() {
        return areaParking;
    }

    public void setAreaParking(String areaParking) {
        this.areaParking = areaParking;
    }

    public String getAreaTrialRun() {
        return areaTrialRun;
    }

    public void setAreaTrialRun(String areaTrialRun) {
        this.areaTrialRun = areaTrialRun;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @Override
    protected Serializable pkVal() {
        return this.entId;
    }

    @Override
    public String toString() {
        return "InspectSpot{" +
        "entId=" + entId +
        ", isFixInspect=" + isFixInspect +
        ", isGrade=" + isGrade +
        ", spotLevel=" + spotLevel +
        ", measureCertificate=" + measureCertificate +
        ", issueDate=" + issueDate +
        ", validDate=" + validDate +
        ", issueUnit=" + issueUnit +
        ", personNum=" + personNum +
        ", equipmentNum=" + equipmentNum +
        ", areaTotal=" + areaTotal +
        ", areaInspect=" + areaInspect +
        ", areaAssistant=" + areaAssistant +
        ", areaParking=" + areaParking +
        ", areaTrialRun=" + areaTrialRun +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        "}";
    }
}
