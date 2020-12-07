package com.ycxc.upload.entity.bus;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class InspectImg extends BaseEntity<InspectImg> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "inspect_img_id", type = IdType.AUTO)
    private Integer inspectImgId;

    @TableField("inspect_id")
    private Integer inspectId;

    @TableField("img")
    private String img;

    @TableField("create_time")
    private Date createTime;


    public Integer getInspectImgId() {
        return inspectImgId;
    }

    public void setInspectImgId(Integer inspectImgId) {
        this.inspectImgId = inspectImgId;
    }

    public Integer getInspectId() {
        return inspectId;
    }

    public void setInspectId(Integer inspectId) {
        this.inspectId = inspectId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.inspectImgId;
    }

    @Override
    public String toString() {
        return "InspectImg{" +
        "inspectImgId=" + inspectImgId +
        ", inspectId=" + inspectId +
        ", img=" + img +
        ", createTime=" + createTime +
        "}";
    }
}
