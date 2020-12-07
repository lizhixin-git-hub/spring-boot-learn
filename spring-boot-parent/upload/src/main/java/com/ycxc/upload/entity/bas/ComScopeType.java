package com.ycxc.upload.entity.bas;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ycxc.upload.entity.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 车辆单位经营范围类型(新加)
 * </p>
 *
 * @author hack2003
 * @since 2019-07-26
 */
public class ComScopeType extends BaseEntity<ComScopeType> {

    private static final long serialVersionUID = 1L;

    /**
     * scope_type_id
     */
    @TableId(value = "scope_type_id", type = IdType.AUTO)
    private Integer scopeTypeId;

    /**
     * 经营类型名称
     */
    @TableField("typename")
    private String typename;


    public Integer getScopeTypeId() {
        return scopeTypeId;
    }

    public void setScopeTypeId(Integer scopeTypeId) {
        this.scopeTypeId = scopeTypeId;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    protected Serializable pkVal() {
        return this.scopeTypeId;
    }

    @Override
    public String toString() {
        return "ComScopeType{" +
        "scopeTypeId=" + scopeTypeId +
        ", typename=" + typename +
        "}";
    }
}
