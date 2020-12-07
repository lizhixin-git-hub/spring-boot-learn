package com.ycxc.upload.entity.bas;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ycxc.upload.entity.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 车辆单位经营范围(新加)
 * </p>
 *
 * @author hack2003
 * @since 2019-07-26
 */
public class ComScope extends BaseEntity<ComScope> {

    private static final long serialVersionUID = 1L;

    /**
     * com_scope_id
     */
    @TableId(value = "com_scope_id", type = IdType.AUTO)
    private Integer comScopeId;

    /**
     * 车辆单位ID
     */
    @TableField("vehicle_company_id")
    private Integer vehicleCompanyId;

    /**
     * 经营范围类型ID
     */
    @TableField("scope_type_id")
    private Integer scopeTypeId;


    public Integer getComScopeId() {
        return comScopeId;
    }

    public void setComScopeId(Integer comScopeId) {
        this.comScopeId = comScopeId;
    }

    public Integer getVehicleCompanyId() {
        return vehicleCompanyId;
    }

    public void setVehicleCompanyId(Integer vehicleCompanyId) {
        this.vehicleCompanyId = vehicleCompanyId;
    }

    public Integer getScopeTypeId() {
        return scopeTypeId;
    }

    public void setScopeTypeId(Integer scopeTypeId) {
        this.scopeTypeId = scopeTypeId;
    }

    @Override
    protected Serializable pkVal() {
        return this.comScopeId;
    }

    @Override
    public String toString() {
        return "ComScope{" +
        "comScopeId=" + comScopeId +
        ", vehicleCompanyId=" + vehicleCompanyId +
        ", scopeTypeId=" + scopeTypeId +
        "}";
    }
}
