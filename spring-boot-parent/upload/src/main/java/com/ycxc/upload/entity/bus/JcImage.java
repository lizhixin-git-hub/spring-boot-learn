package com.ycxc.upload.entity.bus;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ycxc.upload.entity.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 图片资料
 * </p>
 *
 * @author hack2003
 * @since 2019-07-26
 */
public class JcImage extends BaseEntity<JcImage> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "imageid", type = IdType.AUTO)
    private Integer imageid;

    /**
     * 检测流水号  检测流水号编码规则：行政区划代码（6位）+检验检测机
            构代码（3位）+年月日（YYYYMMDD）+检验序号（4位），其中“检
            验序号”按当日检车数量的次序
     */
    @TableField("detectSn")
    private String detectSn;

    /**
     * 图片链接地址
     */
    @TableField("imageUrl")
    private String imageUrl;

    /**
     * 类型（0-工位、1-安检）
     */
    @TableField("type")
    private Integer type;

    /**
     * 检验工位照片类型,见
            附录B.11；安检结论图片信息代
            码，见附录B.15 
            
     */
    @TableField("imageType")
    private String imageType;

    /**
     * 图片信息，经base64编
            码后字符串
            
     */
    @TableField("base64Image")
    private String base64Image;

    /**
     * 对应inspect表中的test_id
     */
    @TableField("inpsectId")
    private Integer inpsectId;


    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    public String getDetectSn() {
        return detectSn;
    }

    public void setDetectSn(String detectSn) {
        this.detectSn = detectSn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public Integer getInpsectId() {
        return inpsectId;
    }

    public void setInpsectId(Integer inpsectId) {
        this.inpsectId = inpsectId;
    }

    @Override
    protected Serializable pkVal() {
        return this.imageid;
    }

    @Override
    public String toString() {
        return "JcImage{" +
        "imageid=" + imageid +
        ", detectSn=" + detectSn +
        ", imageUrl=" + imageUrl +
        ", type=" + type +
        ", imageType=" + imageType +
        ", base64Image=" + base64Image +
        ", inpsectId=" + inpsectId +
        "}";
    }
}
