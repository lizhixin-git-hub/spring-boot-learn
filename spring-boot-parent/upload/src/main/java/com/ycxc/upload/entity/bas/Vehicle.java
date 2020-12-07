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
public class Vehicle extends BaseEntity<Vehicle> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "vehicle_id", type = IdType.AUTO)
    private Integer vehicleId;

    @TableField("card_no")
    private String cardNo;

    @TableField("vehicle_company_id")
    private Integer vehicleCompanyId;

    /**
     * 填录入车辆信息的企业ID
     */
    @TableField("ent_id")
    private Integer entId;

    @TableField("licence_no")
    private String licenceNo;

    /**
     * [BLUE:1-蓝色; YELLOW:2-黄色; FARM_YELLOW:3-农黄; GREEN:4-绿色; BLACK:5-黑色; WHITE:6-白色]
     */
    @TableField("licence_color")
    private Integer licenceColor;

    @TableField("vehicle_type_code")
    private String vehicleTypeCode;

    @TableField("manage_licence")
    private String manageLicence;

    /**
     * [NO:0-否; YES:1-是]
     */
    @TableField("is_private")
    private Integer isPrivate;

    @TableField("owner_name")
    private String ownerName;

    @TableField("owner_address")
    private String ownerAddress;

    @TableField("owner_phone")
    private String ownerPhone;

    @TableField("owner_postcode")
    private String ownerPostcode;

    @TableField("owner_fax")
    private String ownerFax;

    @TableField("owner_email")
    private String ownerEmail;

    /**
     * 不用字段
     */
    @TableField("owner_area")
    private String ownerArea;

    @TableField("oil_type")
    private String oilType;

    @TableField("driver_name")
    private String driverName;

    @TableField("engine_no")
    private String engineNo;

    @TableField("under_pan_no")
    private String underPanNo;

    @TableField("drive_type")
    private String driveType;

    @TableField("engine_type")
    private String engineType;

    @TableField("run_date")
    private String runDate;

    @TableField("out_date")
    private String outDate;

    @TableField("rated_power")
    private String ratedPower;

    @TableField("rated_oil")
    private String ratedOil;

    @TableField("lamp_num")
    private String lampNum;

    @TableField("axes_num")
    private String axesNum;

    @TableField("fix_finish_no")
    private String fixFinishNo;

    @TableField("suspend_form")
    private String suspendForm;

    @TableField("out_line_size")
    private String outLineSize;

    @TableField("country")
    private String country;

    @TableField("car_supplier")
    private String carSupplier;

    @TableField("produce_area")
    private String produceArea;

    @TableField("auto_color")
    private String autoColor;

    @TableField("paint_no")
    private String paintNo;

    @TableField("axes_distance")
    private String axesDistance;

    @TableField("wheel_distance")
    private String wheelDistance;

    @TableField("tyre_size")
    private String tyreSize;

    @TableField("auto_character")
    private String autoCharacter;

    @TableField("yingyun_no")
    private String yingyunNo;

    @TableField("popedom")
    private String popedom;

    @TableField("archives_date")
    private Date archivesDate;

    @TableField("old_mileage")
    private String oldMileage;

    @TableField("ton")
    private BigDecimal ton;

    @TableField("seat")
    private Integer seat;

    @TableField("empty_weight")
    private String emptyWeight;

    @TableField("full_weight")
    private String fullWeight;

    /**
     * [OK:0-正常; CANCEL:1-注销; WAIT_CHECK:2-待审核; CHECK_FAIL:3-审核失败]
     */
    @TableField("status")
    private Integer status;

    @TableField("create_date")
    private Date createDate;

    @TableField("create_by")
    private Integer createBy;

    /**
     * [MANAGE:1-管理; ENT:2-企业; OWNER:3-车主]
     */
    @TableField("create_type")
    private Integer createType;

    /**
     * [NO:0-否; YES:1-是]
     */
    @TableField("is_other_place")
    private Integer isOtherPlace;

    @TableField("visa_date")
    private Date visaDate;

    @TableField("next_visa_date")
    private Date nextVisaDate;

    @TableField("grade_date")
    private Date gradeDate;

    @TableField("next_grade_date")
    private Date nextGradeDate;

    @TableField("visa_mil")
    private Integer visaMil;

    @TableField("next_visa_mil")
    private Integer nextVisaMil;

    /**
     * 月数
     */
    @TableField("fix_period")
    private Integer fixPeriod;

    /**
     * 里程间隔，公里
     */
    @TableField("fix_mil")
    private Integer fixMil;

    /**
     * [NO:0-否; YES:1-是]
     */
    @TableField("sync_flag")
    private Integer syncFlag;

    @TableField("reg_img")
    private String regImg;

    @TableField("oper_cert_img")
    private String operCertImg;

    @TableField("axe_num_img")
    private String axeNumImg;

    @TableField("licence_img")
    private String licenceImg;

    @TableField("bus_fix_level")
    private Integer busFixLevel;

    @TableField("vehicle_industry_code")
    private String vehicleIndustryCode;

    @TableField("vehicle_long")
    private Integer vehicleLong;

    @TableField("vehicle_width")
    private Integer vehicleWidth;

    @TableField("vehicle_high")
    private Integer vehicleHigh;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    @TableField("is_Allot")
    private Integer isAllot;

    /**
     * 0---自有车，1--挂靠车
     */
    @TableField("attribute")
    private Integer attribute;

    @TableField("coronae")
    private String coronae;

    @TableField("quality")
    private String quality;

    @TableField("road")
    private String road;

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
     * 运输车辆id
     */
    @TableField("ys_vehicle_id")
    private Integer ysVehicleId;

    /**
     * [NO:0-否; YES:1-是]
     */
    @TableField("sync_plat_edit_flag")
    private Integer syncPlatEditFlag;

    /**
     * [NO:0-否; YES:1-是]
     */
    @TableField("sync_plat_del_flag")
    private Integer syncPlatDelFlag;

    /**
     * [NO:0-否; YES:1-是]
     */
    @TableField("sync_plat_new_flag")
    private Integer syncPlatNewFlag;

    /**
     * 车辆状态：5正常，0车辆停驶，1车辆封存，2.启封使用，3报废，4其它
     */
    @TableField("vehicle_status")
    private Integer vehicleStatus;

    /**
     * 1 表示新车 0是非新车
     */
    @TableField("new_car")
    private Integer newCar;

    @TableField("old_vehicleNo")
    private String oldVehicleno;

    @TableField("old_vehicle_company")
    private String oldVehicleCompany;

    /**
     * [ NONE:0-已解锁;EXIST:1-已锁定]
     */
    @TableField("locked")
    private Integer locked;

    /**
     * vin码，17位英数组合
     */
    @TableField("vin")
    private String vin;

    /**
     * 车辆拍照张数
     */
    @TableField("photo_num")
    private Integer photoNum;

    /**
     * 车辆拍照时间间隔（逗号分隔）
     */
    @TableField("photo_gap")
    private String photoGap;

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getVehicleCompanyId() {
        return vehicleCompanyId;
    }

    public void setVehicleCompanyId(Integer vehicleCompanyId) {
        this.vehicleCompanyId = vehicleCompanyId;
    }

    public Integer getEntId() {
        return entId;
    }

    public void setEntId(Integer entId) {
        this.entId = entId;
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

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getManageLicence() {
        return manageLicence;
    }

    public void setManageLicence(String manageLicence) {
        this.manageLicence = manageLicence;
    }

    public Integer getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerPostcode() {
        return ownerPostcode;
    }

    public void setOwnerPostcode(String ownerPostcode) {
        this.ownerPostcode = ownerPostcode;
    }

    public String getOwnerFax() {
        return ownerFax;
    }

    public void setOwnerFax(String ownerFax) {
        this.ownerFax = ownerFax;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerArea() {
        return ownerArea;
    }

    public void setOwnerArea(String ownerArea) {
        this.ownerArea = ownerArea;
    }

    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getUnderPanNo() {
        return underPanNo;
    }

    public void setUnderPanNo(String underPanNo) {
        this.underPanNo = underPanNo;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getRunDate() {
        return runDate;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getRatedPower() {
        return ratedPower;
    }

    public void setRatedPower(String ratedPower) {
        this.ratedPower = ratedPower;
    }

    public String getRatedOil() {
        return ratedOil;
    }

    public void setRatedOil(String ratedOil) {
        this.ratedOil = ratedOil;
    }

    public String getLampNum() {
        return lampNum;
    }

    public void setLampNum(String lampNum) {
        this.lampNum = lampNum;
    }

    public String getAxesNum() {
        return axesNum;
    }

    public void setAxesNum(String axesNum) {
        this.axesNum = axesNum;
    }

    public String getFixFinishNo() {
        return fixFinishNo;
    }

    public void setFixFinishNo(String fixFinishNo) {
        this.fixFinishNo = fixFinishNo;
    }

    public String getSuspendForm() {
        return suspendForm;
    }

    public void setSuspendForm(String suspendForm) {
        this.suspendForm = suspendForm;
    }

    public String getOutLineSize() {
        return outLineSize;
    }

    public void setOutLineSize(String outLineSize) {
        this.outLineSize = outLineSize;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCarSupplier() {
        return carSupplier;
    }

    public void setCarSupplier(String carSupplier) {
        this.carSupplier = carSupplier;
    }

    public String getProduceArea() {
        return produceArea;
    }

    public void setProduceArea(String produceArea) {
        this.produceArea = produceArea;
    }

    public String getAutoColor() {
        return autoColor;
    }

    public void setAutoColor(String autoColor) {
        this.autoColor = autoColor;
    }

    public String getPaintNo() {
        return paintNo;
    }

    public void setPaintNo(String paintNo) {
        this.paintNo = paintNo;
    }

    public String getAxesDistance() {
        return axesDistance;
    }

    public void setAxesDistance(String axesDistance) {
        this.axesDistance = axesDistance;
    }

    public String getWheelDistance() {
        return wheelDistance;
    }

    public void setWheelDistance(String wheelDistance) {
        this.wheelDistance = wheelDistance;
    }

    public String getTyreSize() {
        return tyreSize;
    }

    public void setTyreSize(String tyreSize) {
        this.tyreSize = tyreSize;
    }

    public String getAutoCharacter() {
        return autoCharacter;
    }

    public void setAutoCharacter(String autoCharacter) {
        this.autoCharacter = autoCharacter;
    }

    public String getYingyunNo() {
        return yingyunNo;
    }

    public void setYingyunNo(String yingyunNo) {
        this.yingyunNo = yingyunNo;
    }

    public String getPopedom() {
        return popedom;
    }

    public void setPopedom(String popedom) {
        this.popedom = popedom;
    }

    public Date getArchivesDate() {
        return archivesDate;
    }

    public void setArchivesDate(Date archivesDate) {
        this.archivesDate = archivesDate;
    }

    public String getOldMileage() {
        return oldMileage;
    }

    public void setOldMileage(String oldMileage) {
        this.oldMileage = oldMileage;
    }

    public BigDecimal getTon() {
        return ton;
    }

    public void setTon(BigDecimal ton) {
        this.ton = ton;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public String getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(String emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    public String getFullWeight() {
        return fullWeight;
    }

    public void setFullWeight(String fullWeight) {
        this.fullWeight = fullWeight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getCreateType() {
        return createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    public Integer getIsOtherPlace() {
        return isOtherPlace;
    }

    public void setIsOtherPlace(Integer isOtherPlace) {
        this.isOtherPlace = isOtherPlace;
    }

    public Date getVisaDate() {
        return visaDate;
    }

    public void setVisaDate(Date visaDate) {
        this.visaDate = visaDate;
    }

    public Date getNextVisaDate() {
        return nextVisaDate;
    }

    public void setNextVisaDate(Date nextVisaDate) {
        this.nextVisaDate = nextVisaDate;
    }

    public Date getGradeDate() {
        return gradeDate;
    }

    public void setGradeDate(Date gradeDate) {
        this.gradeDate = gradeDate;
    }

    public Date getNextGradeDate() {
        return nextGradeDate;
    }

    public void setNextGradeDate(Date nextGradeDate) {
        this.nextGradeDate = nextGradeDate;
    }

    public Integer getVisaMil() {
        return visaMil;
    }

    public void setVisaMil(Integer visaMil) {
        this.visaMil = visaMil;
    }

    public Integer getNextVisaMil() {
        return nextVisaMil;
    }

    public void setNextVisaMil(Integer nextVisaMil) {
        this.nextVisaMil = nextVisaMil;
    }

    public Integer getFixPeriod() {
        return fixPeriod;
    }

    public void setFixPeriod(Integer fixPeriod) {
        this.fixPeriod = fixPeriod;
    }

    public Integer getFixMil() {
        return fixMil;
    }

    public void setFixMil(Integer fixMil) {
        this.fixMil = fixMil;
    }

    public Integer getSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(Integer syncFlag) {
        this.syncFlag = syncFlag;
    }

    public String getRegImg() {
        return regImg;
    }

    public void setRegImg(String regImg) {
        this.regImg = regImg;
    }

    public String getOperCertImg() {
        return operCertImg;
    }

    public void setOperCertImg(String operCertImg) {
        this.operCertImg = operCertImg;
    }

    public String getAxeNumImg() {
        return axeNumImg;
    }

    public void setAxeNumImg(String axeNumImg) {
        this.axeNumImg = axeNumImg;
    }

    public String getLicenceImg() {
        return licenceImg;
    }

    public void setLicenceImg(String licenceImg) {
        this.licenceImg = licenceImg;
    }

    public Integer getBusFixLevel() {
        return busFixLevel;
    }

    public void setBusFixLevel(Integer busFixLevel) {
        this.busFixLevel = busFixLevel;
    }

    public String getVehicleIndustryCode() {
        return vehicleIndustryCode;
    }

    public void setVehicleIndustryCode(String vehicleIndustryCode) {
        this.vehicleIndustryCode = vehicleIndustryCode;
    }

    public Integer getVehicleLong() {
        return vehicleLong;
    }

    public void setVehicleLong(Integer vehicleLong) {
        this.vehicleLong = vehicleLong;
    }

    public Integer getVehicleWidth() {
        return vehicleWidth;
    }

    public void setVehicleWidth(Integer vehicleWidth) {
        this.vehicleWidth = vehicleWidth;
    }

    public Integer getVehicleHigh() {
        return vehicleHigh;
    }

    public void setVehicleHigh(Integer vehicleHigh) {
        this.vehicleHigh = vehicleHigh;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsAllot() {
        return isAllot;
    }

    public void setIsAllot(Integer isAllot) {
        this.isAllot = isAllot;
    }

    public Integer getAttribute() {
        return attribute;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    public String getCoronae() {
        return coronae;
    }

    public void setCoronae(String coronae) {
        this.coronae = coronae;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
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

    public Integer getYsVehicleId() {
        return ysVehicleId;
    }

    public void setYsVehicleId(Integer ysVehicleId) {
        this.ysVehicleId = ysVehicleId;
    }

    public Integer getSyncPlatEditFlag() {
        return syncPlatEditFlag;
    }

    public void setSyncPlatEditFlag(Integer syncPlatEditFlag) {
        this.syncPlatEditFlag = syncPlatEditFlag;
    }

    public Integer getSyncPlatDelFlag() {
        return syncPlatDelFlag;
    }

    public void setSyncPlatDelFlag(Integer syncPlatDelFlag) {
        this.syncPlatDelFlag = syncPlatDelFlag;
    }

    public Integer getSyncPlatNewFlag() {
        return syncPlatNewFlag;
    }

    public void setSyncPlatNewFlag(Integer syncPlatNewFlag) {
        this.syncPlatNewFlag = syncPlatNewFlag;
    }

    public Integer getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(Integer vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public Integer getNewCar() {
        return newCar;
    }

    public void setNewCar(Integer newCar) {
        this.newCar = newCar;
    }

    public String getOldVehicleno() {
        return oldVehicleno;
    }

    public void setOldVehicleno(String oldVehicleno) {
        this.oldVehicleno = oldVehicleno;
    }

    public String getOldVehicleCompany() {
        return oldVehicleCompany;
    }

    public void setOldVehicleCompany(String oldVehicleCompany) {
        this.oldVehicleCompany = oldVehicleCompany;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getPhotoNum() {
        return photoNum;
    }

    public void setPhotoNum(Integer photoNum) {
        this.photoNum = photoNum;
    }

    public String getPhotoGap() {
        return photoGap;
    }

    public void setPhotoGap(String photoGap) {
        this.photoGap = photoGap;
    }

    @Override
    protected Serializable pkVal() {
        return this.vehicleId;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
        "vehicleId=" + vehicleId +
        ", cardNo=" + cardNo +
        ", vehicleCompanyId=" + vehicleCompanyId +
        ", entId=" + entId +
        ", licenceNo=" + licenceNo +
        ", licenceColor=" + licenceColor +
        ", vehicleTypeCode=" + vehicleTypeCode +
        ", manageLicence=" + manageLicence +
        ", isPrivate=" + isPrivate +
        ", ownerName=" + ownerName +
        ", ownerAddress=" + ownerAddress +
        ", ownerPhone=" + ownerPhone +
        ", ownerPostcode=" + ownerPostcode +
        ", ownerFax=" + ownerFax +
        ", ownerEmail=" + ownerEmail +
        ", ownerArea=" + ownerArea +
        ", oilType=" + oilType +
        ", driverName=" + driverName +
        ", engineNo=" + engineNo +
        ", underPanNo=" + underPanNo +
        ", driveType=" + driveType +
        ", engineType=" + engineType +
        ", runDate=" + runDate +
        ", outDate=" + outDate +
        ", ratedPower=" + ratedPower +
        ", ratedOil=" + ratedOil +
        ", lampNum=" + lampNum +
        ", axesNum=" + axesNum +
        ", fixFinishNo=" + fixFinishNo +
        ", suspendForm=" + suspendForm +
        ", outLineSize=" + outLineSize +
        ", country=" + country +
        ", carSupplier=" + carSupplier +
        ", produceArea=" + produceArea +
        ", autoColor=" + autoColor +
        ", paintNo=" + paintNo +
        ", axesDistance=" + axesDistance +
        ", wheelDistance=" + wheelDistance +
        ", tyreSize=" + tyreSize +
        ", autoCharacter=" + autoCharacter +
        ", yingyunNo=" + yingyunNo +
        ", popedom=" + popedom +
        ", archivesDate=" + archivesDate +
        ", oldMileage=" + oldMileage +
        ", ton=" + ton +
        ", seat=" + seat +
        ", emptyWeight=" + emptyWeight +
        ", fullWeight=" + fullWeight +
        ", status=" + status +
        ", createDate=" + createDate +
        ", createBy=" + createBy +
        ", createType=" + createType +
        ", isOtherPlace=" + isOtherPlace +
        ", visaDate=" + visaDate +
        ", nextVisaDate=" + nextVisaDate +
        ", gradeDate=" + gradeDate +
        ", nextGradeDate=" + nextGradeDate +
        ", visaMil=" + visaMil +
        ", nextVisaMil=" + nextVisaMil +
        ", fixPeriod=" + fixPeriod +
        ", fixMil=" + fixMil +
        ", syncFlag=" + syncFlag +
        ", regImg=" + regImg +
        ", operCertImg=" + operCertImg +
        ", axeNumImg=" + axeNumImg +
        ", licenceImg=" + licenceImg +
        ", busFixLevel=" + busFixLevel +
        ", vehicleIndustryCode=" + vehicleIndustryCode +
        ", vehicleLong=" + vehicleLong +
        ", vehicleWidth=" + vehicleWidth +
        ", vehicleHigh=" + vehicleHigh +
        ", remark=" + remark +
        ", isAllot=" + isAllot +
        ", attribute=" + attribute +
        ", coronae=" + coronae +
        ", quality=" + quality +
        ", road=" + road +
        ", syncYsNewFlag=" + syncYsNewFlag +
        ", syncYsEditFlag=" + syncYsEditFlag +
        ", syncYsDelFlag=" + syncYsDelFlag +
        ", ysVehicleId=" + ysVehicleId +
        ", syncPlatEditFlag=" + syncPlatEditFlag +
        ", syncPlatDelFlag=" + syncPlatDelFlag +
        ", syncPlatNewFlag=" + syncPlatNewFlag +
        ", vehicleStatus=" + vehicleStatus +
        ", newCar=" + newCar +
        ", oldVehicleno=" + oldVehicleno +
        ", oldVehicleCompany=" + oldVehicleCompany +
        ", locked=" + locked +
        ", vin=" + vin +
        ", photoNum=" + photoNum +
        ", photoGap=" + photoGap +
        "}";
    }
}
