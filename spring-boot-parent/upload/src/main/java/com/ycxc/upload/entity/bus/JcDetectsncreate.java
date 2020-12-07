package com.ycxc.upload.entity.bus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ycxc.upload.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 检测流水号生成
 * </p>
 *
 * @author hack2003
 * @since 2019-07-26
 */
public class JcDetectsncreate extends BaseEntity<JcDetectsncreate> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "detectSnCreateid", type = IdType.AUTO)
    private Integer detectSnCreateid;

    /**
     * 检测流水号  检测流水号编码规则：行政区划代码（6位）+检验检测机
            构代码（3位）+年月日（YYYYMMDD）+检验序号（4位），其中“检
            验序号”按当日检车数量的次序
     */
    @TableField("detectSn")
    private String detectSn;

    /**
     * 检验检测机构唯一
            编码,检验检测机构唯一编码由行政区划代码（6位）和检验检测
            机构代码（3位）组成
     */
    @TableField("dsId")
    private String dsId;

    /**
     * 车牌号码
     */
    @TableField("vehicleNo")
    private String vehicleNo;

    /**
     * 车牌颜色代码
     */
    @TableField("plateColorCode")
    private Integer plateColorCode;

    /**
     * 车辆识别代码，填写17位VIN号码
     */
    @TableField("vinNo")
    private String vinNo;

    /**
     * 流水号状态 0-- 未完成检测，1-- 已完成检测
     */
    @TableField("detectSnStatus")
    private Integer detectSnStatus;

    @TableField("createTime")
    private Date createTime;

    /**
     * 省网机构流水号编码
     */
    @TableField("detectSn_province")
    private String detectsnProvince;

    /**
     * 省网检测数据校验码
     */
    @TableField("checkcode")
    private String checkcode;

    /**
     * 省网检测站编号
     */
    @TableField("dsId_province")
    private String dsidProvince;


    public Integer getDetectSnCreateid() {
        return detectSnCreateid;
    }

    public void setDetectSnCreateid(Integer detectSnCreateid) {
        this.detectSnCreateid = detectSnCreateid;
    }

    public String getDetectSn() {
        return detectSn;
    }

    public void setDetectSn(String detectSn) {
        this.detectSn = detectSn;
    }

    public String getDsId() {
        return dsId;
    }

    public void setDsId(String dsId) {
        this.dsId = dsId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Integer getPlateColorCode() {
        return plateColorCode;
    }

    public void setPlateColorCode(Integer plateColorCode) {
        this.plateColorCode = plateColorCode;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public Integer getDetectSnStatus() {
        return detectSnStatus;
    }

    public void setDetectSnStatus(Integer detectSnStatus) {
        this.detectSnStatus = detectSnStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDetectsnProvince() {
        return detectsnProvince;
    }

    public void setDetectsnProvince(String detectsnProvince) {
        this.detectsnProvince = detectsnProvince;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public String getDsidProvince() {
        return dsidProvince;
    }

    public void setDsidProvince(String dsidProvince) {
        this.dsidProvince = dsidProvince;
    }

    @Override
    protected Serializable pkVal() {
        return this.detectSnCreateid;
    }

    @Override
    public String toString() {
        return "JcDetectsncreate{" +
        "detectSnCreateid=" + detectSnCreateid +
        ", detectSn=" + detectSn +
        ", dsId=" + dsId +
        ", vehicleNo=" + vehicleNo +
        ", plateColorCode=" + plateColorCode +
        ", vinNo=" + vinNo +
        ", detectSnStatus=" + detectSnStatus +
        ", createTime=" + createTime +
        ", detectsnProvince=" + detectsnProvince +
        ", checkcode=" + checkcode +
        ", dsidProvince=" + dsidProvince +
        "}";
    }
}
