package com.ycxc.upload.entity.bas;

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
 * @since 2019-07-26
 */
public class VehicleCompany extends BaseEntity<VehicleCompany> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "vehicle_company_id", type = IdType.AUTO)
    private Integer vehicleCompanyId;

    @TableField("company_name")
    private String companyName;

    @TableField("address")
    private String address;

    @TableField("phone")
    private String phone;

    @TableField("postcode")
    private String postcode;

    @TableField("fax")
    private String fax;

    @TableField("email")
    private String email;

    @TableField("user_name")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("login_ip")
    private String loginIp;

    @TableField("login_time")
    private Date loginTime;

    @TableField("login_times")
    private Integer loginTimes;

    @TableField("update_time")
    private Date updateTime;

    /**
     * [OK:0-正常; CANCEL:1-注销]
     */
    @TableField("status")
    private Integer status;

    /**
     * [NO:0-否; YES:1-是]
     */
    @TableField("sync_flag")
    private Integer syncFlag;

    @TableField("company_no")
    private String companyNo;

    @TableField("province")
    private String province;

    @TableField("city")
    private String city;

    @TableField("district")
    private String district;

    @TableField("contact_person")
    private String contactPerson;

    @TableField("licence_no")
    private String licenceNo;

    @TableField("legal_person")
    private String legalPerson;

    @TableField("fund_amount")
    private String fundAmount;

    @TableField("economy_kind")
    private Integer economyKind;

    @TableField("balance_amt")
    private BigDecimal balanceAmt;

    @TableField("permit_fix_num")
    private Integer permitFixNum;

    @TableField("pemit_fix_flag")
    private Integer pemitFixFlag;

    @TableField("permit_fix_used")
    private Integer permitFixUsed;

    /**
     * [NO:0-否; YES:1-是]
     */
    @TableField("sync_ys_new_flag")
    private Integer syncYsNewFlag;

    /**
     * [NO:0-否; YES:1-是]
     */
    @TableField("sync_ys_edit_flag")
    private Integer syncYsEditFlag;

    /**
     * [NO:0-否; YES:1-是]
     */
    @TableField("sync_ys_del_flag")
    private Integer syncYsDelFlag;

    /**
     * 运输车辆单位id
     */
    @TableField("ys_vehicle_company_id")
    private Integer ysVehicleCompanyId;

    /**
     * 0:不同意协议   1：同意协议
     */
    @TableField("agreement_flag")
    private Integer agreementFlag;

    /**
     * 数据中心ID
     */
    @TableField("dc_id")
    private String dcId;


    public Integer getVehicleCompanyId() {
        return vehicleCompanyId;
    }

    public void setVehicleCompanyId(Integer vehicleCompanyId) {
        this.vehicleCompanyId = vehicleCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(Integer syncFlag) {
        this.syncFlag = syncFlag;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getFundAmount() {
        return fundAmount;
    }

    public void setFundAmount(String fundAmount) {
        this.fundAmount = fundAmount;
    }

    public Integer getEconomyKind() {
        return economyKind;
    }

    public void setEconomyKind(Integer economyKind) {
        this.economyKind = economyKind;
    }

    public BigDecimal getBalanceAmt() {
        return balanceAmt;
    }

    public void setBalanceAmt(BigDecimal balanceAmt) {
        this.balanceAmt = balanceAmt;
    }

    public Integer getPermitFixNum() {
        return permitFixNum;
    }

    public void setPermitFixNum(Integer permitFixNum) {
        this.permitFixNum = permitFixNum;
    }

    public Integer getPemitFixFlag() {
        return pemitFixFlag;
    }

    public void setPemitFixFlag(Integer pemitFixFlag) {
        this.pemitFixFlag = pemitFixFlag;
    }

    public Integer getPermitFixUsed() {
        return permitFixUsed;
    }

    public void setPermitFixUsed(Integer permitFixUsed) {
        this.permitFixUsed = permitFixUsed;
    }

    public Integer getSyncYsNewFlag() {
        return syncYsNewFlag;
    }

    public void setSyncYsNewFlag(Integer syncYsNewFlag) {
        this.syncYsNewFlag = syncYsNewFlag;
    }

    public Integer getSyncYsEditFlag() {
        return syncYsEditFlag;
    }

    public void setSyncYsEditFlag(Integer syncYsEditFlag) {
        this.syncYsEditFlag = syncYsEditFlag;
    }

    public Integer getSyncYsDelFlag() {
        return syncYsDelFlag;
    }

    public void setSyncYsDelFlag(Integer syncYsDelFlag) {
        this.syncYsDelFlag = syncYsDelFlag;
    }

    public Integer getYsVehicleCompanyId() {
        return ysVehicleCompanyId;
    }

    public void setYsVehicleCompanyId(Integer ysVehicleCompanyId) {
        this.ysVehicleCompanyId = ysVehicleCompanyId;
    }

    public Integer getAgreementFlag() {
        return agreementFlag;
    }

    public void setAgreementFlag(Integer agreementFlag) {
        this.agreementFlag = agreementFlag;
    }

    public String getDcId() {
        return dcId;
    }

    public void setDcId(String dcId) {
        this.dcId = dcId;
    }

    @Override
    protected Serializable pkVal() {
        return this.vehicleCompanyId;
    }

    @Override
    public String toString() {
        return "VehicleCompany{" +
        "vehicleCompanyId=" + vehicleCompanyId +
        ", companyName=" + companyName +
        ", address=" + address +
        ", phone=" + phone +
        ", postcode=" + postcode +
        ", fax=" + fax +
        ", email=" + email +
        ", userName=" + userName +
        ", password=" + password +
        ", loginIp=" + loginIp +
        ", loginTime=" + loginTime +
        ", loginTimes=" + loginTimes +
        ", updateTime=" + updateTime +
        ", status=" + status +
        ", syncFlag=" + syncFlag +
        ", companyNo=" + companyNo +
        ", province=" + province +
        ", city=" + city +
        ", district=" + district +
        ", contactPerson=" + contactPerson +
        ", licenceNo=" + licenceNo +
        ", legalPerson=" + legalPerson +
        ", fundAmount=" + fundAmount +
        ", economyKind=" + economyKind +
        ", balanceAmt=" + balanceAmt +
        ", permitFixNum=" + permitFixNum +
        ", pemitFixFlag=" + pemitFixFlag +
        ", permitFixUsed=" + permitFixUsed +
        ", syncYsNewFlag=" + syncYsNewFlag +
        ", syncYsEditFlag=" + syncYsEditFlag +
        ", syncYsDelFlag=" + syncYsDelFlag +
        ", ysVehicleCompanyId=" + ysVehicleCompanyId +
        ", agreementFlag=" + agreementFlag +
        ", dcId=" + dcId +
        "}";
    }
}
