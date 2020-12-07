package com.ycxc.upload.entity.bus;

import java.math.BigDecimal;
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
 * @since 2019-07-30
 */
public class EntCostdetail extends BaseEntity<EntCostdetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 企业费用消费记录ID
     */
    @TableId(value = "ent_costdetail_id", type = IdType.AUTO)
    private Integer entCostdetailId;

    /**
     * 企业ID
     */
    @TableField("ent_id")
    private Integer entId;

    /**
     * [SERVICE_CHARGE:0-服务费;CARD_CHARGE:1-识别卡费;MONTH_SERVICE_CHARGE:2-月结服务费;OTHERS:3-其他费用]
     */
    @TableField("cost_type")
    private Integer costType;

    /**
     * 金额
     */
    @TableField("amt")
    private BigDecimal amt;

    /**
     * 消费时间
     */
    @TableField("cost_time")
    private Date costTime;

    /**
     * [OK:0-正常; CANCEL:1-注销; WAIT_CHECK:2-待审核; CHECK_FAIL:3-审核失败]
     */
    @TableField("is_delete")
    private Integer isDelete;

    /**
     * 操作员
     */
    @TableField("oper")
    private Integer oper;

    @TableField("balance_salary")
    private BigDecimal balanceSalary;

    @TableField("billid")
    private Integer billid;

    @TableField("ent_permit_fix_id")
    private Integer entPermitFixId;

    /**
     * 0:正常扣费;1:补扣扣费
     */
    @TableField("is_later_cost")
    private Integer isLaterCost;

    /**
     * 0:补扣失败；1：补扣成功;该项仅仅在扣费失败标识有效时生效
     */
    @TableField("later_cost_succ_flag")
    private Integer laterCostSuccFlag;

    /**
     * 检测id
     */
    @TableField("test_id")
    private Integer testId;

    /**
     * 作业id
     */
    @TableField("fix_id")
    private Integer fixId;

    /**
     * 0：扣费成功；1：扣费失败  该项仅仅针对cost_failed_flag=1时生效
     */
    @TableField("cost_failed_flag")
    private Integer costFailedFlag;

    @TableField("licence_no")
    private String licenceNo;

    /**
     * [BLUE:1-蓝色; YELLOW:2-黄色; FARM_YELLOW:3-农黄; GREEN:4-绿色; BLACK:5-黑色; WHITE:6-白色]
     */
    @TableField("licence_color")
    private Integer licenceColor;


    public Integer getEntCostdetailId() {
        return entCostdetailId;
    }

    public void setEntCostdetailId(Integer entCostdetailId) {
        this.entCostdetailId = entCostdetailId;
    }

    public Integer getEntId() {
        return entId;
    }

    public void setEntId(Integer entId) {
        this.entId = entId;
    }

    public Integer getCostType() {
        return costType;
    }

    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public Date getCostTime() {
        return costTime;
    }

    public void setCostTime(Date costTime) {
        this.costTime = costTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getOper() {
        return oper;
    }

    public void setOper(Integer oper) {
        this.oper = oper;
    }

    public BigDecimal getBalanceSalary() {
        return balanceSalary;
    }

    public void setBalanceSalary(BigDecimal balanceSalary) {
        this.balanceSalary = balanceSalary;
    }

    public Integer getBillid() {
        return billid;
    }

    public void setBillid(Integer billid) {
        this.billid = billid;
    }

    public Integer getEntPermitFixId() {
        return entPermitFixId;
    }

    public void setEntPermitFixId(Integer entPermitFixId) {
        this.entPermitFixId = entPermitFixId;
    }

    public Integer getIsLaterCost() {
        return isLaterCost;
    }

    public void setIsLaterCost(Integer isLaterCost) {
        this.isLaterCost = isLaterCost;
    }

    public Integer getLaterCostSuccFlag() {
        return laterCostSuccFlag;
    }

    public void setLaterCostSuccFlag(Integer laterCostSuccFlag) {
        this.laterCostSuccFlag = laterCostSuccFlag;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getFixId() {
        return fixId;
    }

    public void setFixId(Integer fixId) {
        this.fixId = fixId;
    }

    public Integer getCostFailedFlag() {
        return costFailedFlag;
    }

    public void setCostFailedFlag(Integer costFailedFlag) {
        this.costFailedFlag = costFailedFlag;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public Integer getLicenceColor() {
        return licenceColor;
    }

    public void setLicenceColor(Integer licenceColor) {
        this.licenceColor = licenceColor;
    }

    @Override
    protected Serializable pkVal() {
        return this.entCostdetailId;
    }

    @Override
    public String toString() {
        return "EntCostdetail{" +
        "entCostdetailId=" + entCostdetailId +
        ", entId=" + entId +
        ", costType=" + costType +
        ", amt=" + amt +
        ", costTime=" + costTime +
        ", isDelete=" + isDelete +
        ", oper=" + oper +
        ", balanceSalary=" + balanceSalary +
        ", billid=" + billid +
        ", entPermitFixId=" + entPermitFixId +
        ", isLaterCost=" + isLaterCost +
        ", laterCostSuccFlag=" + laterCostSuccFlag +
        ", testId=" + testId +
        ", fixId=" + fixId +
        ", costFailedFlag=" + costFailedFlag +
        ", licenceNo=" + licenceNo +
        ", licenceColor=" + licenceColor +
        "}";
    }
}
