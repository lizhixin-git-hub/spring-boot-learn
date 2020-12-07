package com.ycxc.upload.entity.bas;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ycxc.upload.entity.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hack2003
 * @since 2019-07-18
 */
public class VehicleTypeG24 extends BaseEntity<VehicleTypeG24> {

    private static final long serialVersionUID = 1L;

    @TableField("G24Code")
    private String G24Code;

    @TableField("vehicle_type_code")
    private String vehicleTypeCode;

    @TableField("vehicle_type_name")
    private String vehicleTypeName;

    @TableField("fix_hour")
    private String fixHour;

    @TableField("photo_num")
    private String photoNum;

    @TableField("photo_gap")
    private String photoGap;

    @TableField("fix_period")
    private String fixPeriod;

    @TableField("fix_mil")
    private String fixMil;

    @TableField("price")
    private String price;

    @TableField("G24Name")
    private String G24Name;


    public String getG24Code() {
        return G24Code;
    }

    public void setG24Code(String G24Code) {
        this.G24Code = G24Code;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getFixHour() {
        return fixHour;
    }

    public void setFixHour(String fixHour) {
        this.fixHour = fixHour;
    }

    public String getPhotoNum() {
        return photoNum;
    }

    public void setPhotoNum(String photoNum) {
        this.photoNum = photoNum;
    }

    public String getPhotoGap() {
        return photoGap;
    }

    public void setPhotoGap(String photoGap) {
        this.photoGap = photoGap;
    }

    public String getFixPeriod() {
        return fixPeriod;
    }

    public void setFixPeriod(String fixPeriod) {
        this.fixPeriod = fixPeriod;
    }

    public String getFixMil() {
        return fixMil;
    }

    public void setFixMil(String fixMil) {
        this.fixMil = fixMil;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getG24Name() {
        return G24Name;
    }

    public void setG24Name(String G24Name) {
        this.G24Name = G24Name;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "VehicleTypeG24{" +
        "G24Code=" + G24Code +
        ", vehicleTypeCode=" + vehicleTypeCode +
        ", vehicleTypeName=" + vehicleTypeName +
        ", fixHour=" + fixHour +
        ", photoNum=" + photoNum +
        ", photoGap=" + photoGap +
        ", fixPeriod=" + fixPeriod +
        ", fixMil=" + fixMil +
        ", price=" + price +
        ", G24Name=" + G24Name +
        "}";
    }
}
