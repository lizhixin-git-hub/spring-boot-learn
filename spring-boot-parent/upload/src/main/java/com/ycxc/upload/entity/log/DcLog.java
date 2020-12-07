package com.ycxc.upload.entity.log;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ycxc.upload.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 数据上传日志
 * </p>
 *
 * @author hack2003
 * @since 2019-07-23
 */
@TableName("upload_dc_log")
public class DcLog extends BaseEntity<DcLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    /**
     * 请求参数
     */
    @TableField("request_parameters")
    private String requestParameters;

    /**
     * 上传数据主键
     */
    @TableField("upload_data_primary_key")
    private Integer uploadDataPrimaryKey;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 0 — 成功 1 — 失败
     */
    @TableField("status")
    private Integer status;

    /**
     * 上传结果码
     */
    @TableField("status_code")
    private String statusCode;

    /**
     * 上传结果
     */
    @TableField("upload_result")
    private String uploadResult;

    /**
     * 上传类型[0- 维修检测企业 1-运输企业 2 — 维护数据3 — 检测数据]
     */
    @TableField("type")
    private Integer type;

    /**
     * http响应状态
     */
    @TableField("http_status")
    private String httpStatus;


    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(String requestParameters) {
        this.requestParameters = requestParameters;
    }

    public Integer getUploadDataPrimaryKey() {
        return uploadDataPrimaryKey;
    }

    public void setUploadDataPrimaryKey(Integer uploadDataPrimaryKey) {
        this.uploadDataPrimaryKey = uploadDataPrimaryKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getUploadResult() {
        return uploadResult;
    }

    public void setUploadResult(String uploadResult) {
        this.uploadResult = uploadResult;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

    @Override
    public String toString() {
        return "DcLog{" +
        "logId=" + logId +
        ", requestParameters=" + requestParameters +
        ", uploadDataPrimaryKey=" + uploadDataPrimaryKey +
        ", createTime=" + createTime +
        ", status=" + status +
        ", statusCode=" + statusCode +
        ", uploadResult=" + uploadResult +
        ", type=" + type +
        ", httpStatus=" + httpStatus +
        "}";
    }
}
