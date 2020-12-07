package com.ycxc.upload.entity.bas;

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
public class EntFixType extends BaseEntity<EntFixType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ent_fix_type_id", type = IdType.AUTO)
    private Integer entFixTypeId;

    @TableField("ent_id")
    private Integer entId;

    @TableField("vehicle_type_code")
    private String vehicleTypeCode;


    public Integer getEntFixTypeId() {
        return entFixTypeId;
    }

    public void setEntFixTypeId(Integer entFixTypeId) {
        this.entFixTypeId = entFixTypeId;
    }

    public Integer getEntId() {
        return entId;
    }

    public void setEntId(Integer entId) {
        this.entId = entId;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.entFixTypeId;
    }

    @Override
    public String toString() {
        return "EntFixType{" +
        "entFixTypeId=" + entFixTypeId +
        ", entId=" + entId +
        ", vehicleTypeCode=" + vehicleTypeCode +
        "}";
    }
}
