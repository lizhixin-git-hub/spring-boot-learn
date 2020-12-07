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
 * @since 2019-07-26
 */
public class Inspect extends BaseEntity<Inspect> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "test_id", type = IdType.AUTO)
    private Integer testId;

    @TableField("vehicle_id")
    private Integer vehicleId;

    @TableField("ent_id")
    private Integer entId;

    @TableField("work_station_id")
    private Integer workStationId;

    @TableField("fix_rec_id")
    private Integer fixRecId;

    @TableField("car_faty_id")
    private String carFatyId;

    @TableField("if_full_load")
    private String ifFullLoad;

    @TableField("isbigcar")
    private String isbigcar;

    @TableField("lamp_high")
    private BigDecimal lampHigh;

    @TableField("car_new")
    private String carNew;

    @TableField("full_wgt")
    private BigDecimal fullWgt;

    @TableField("inspect_mc")
    private String inspectMc;

    @TableField("start_time")
    private String startTime;

    @TableField("inspect_time")
    private String inspectTime;

    @TableField("inspect_date")
    private String inspectDate;

    @TableField("inspect_num")
    private String inspectNum;

    @TableField("inspect_times")
    private Integer inspectTimes;

    @TableField("inspect_judge_name")
    private String inspectJudgeName;

    @TableField("factory_no")
    private String factoryNo;

    @TableField("license")
    private String license;

    @TableField("first_inspect")
    private String firstInspect;

    @TableField("inspect_judge")
    private String inspectJudge;

    @TableField("grade")
    private String grade;

    @TableField("license_clr")
    private String licenseClr;

    @TableField("yingyun_no")
    private String yingyunNo;

    @TableField("power_no")
    private String powerNo;

    @TableField("underpan_no")
    private String underpanNo;

    @TableField("unit_name")
    private String unitName;

    @TableField("certificate_no")
    private String certificateNo;

    @TableField("factory_name")
    private String factoryName;

    @TableField("car_kind")
    private String carKind;

    @TableField("out_date")
    private String outDate;

    @TableField("run_date")
    private String runDate;

    @TableField("car_seat")
    private String carSeat;

    @TableField("oiltype_mc")
    private String oiltypeMc;

    @TableField("car_color")
    private String carColor;

    @TableField("popedom")
    private String popedom;

    @TableField("drvtype_name")
    private String drvtypeName;

    @TableField("front_lamp")
    private String frontLamp;

    @TableField("axis_num")
    private Integer axisNum;

    @TableField("drv_axis")
    private String drvAxis;

    @TableField("handbrk_axis")
    private String handbrkAxis;

    @TableField("isroad")
    private String isroad;

    @TableField("checker1person")
    private String checker1person;

    @TableField("checker2person")
    private String checker2person;

    @TableField("checker3person")
    private String checker3person;

    @TableField("leader_name")
    private String leaderName;

    @TableField("hmo_indephi")
    private Integer hmoIndephi;

    @TableField("createby")
    private String createby;

    @TableField("hmo_turbo")
    private Integer hmoTurbo;

    @TableField("chengyun")
    private String chengyun;

    @TableField("dianpen")
    private String dianpen;

    @TableField("organ")
    private String organ;

    @TableField("modifydate")
    private Date modifydate;

    @TableField("inspect")
    private String inspect;

    /**
     * [NO:0-否; YES:1-是]
     */
    @TableField("is_visa")
    private Integer isVisa;

    @TableField("sync_ds_flag")
    private Integer syncDsFlag;

    @TableField("file_flag")
    private Integer fileFlag;

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
     * 检测流水号
     */
    @TableField("detect_sn")
    private String detectSn;

    @TableField("checkcode")
    private String checkcode;


    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getEntId() {
        return entId;
    }

    public void setEntId(Integer entId) {
        this.entId = entId;
    }

    public Integer getWorkStationId() {
        return workStationId;
    }

    public void setWorkStationId(Integer workStationId) {
        this.workStationId = workStationId;
    }

    public Integer getFixRecId() {
        return fixRecId;
    }

    public void setFixRecId(Integer fixRecId) {
        this.fixRecId = fixRecId;
    }

    public String getCarFatyId() {
        return carFatyId;
    }

    public void setCarFatyId(String carFatyId) {
        this.carFatyId = carFatyId;
    }

    public String getIfFullLoad() {
        return ifFullLoad;
    }

    public void setIfFullLoad(String ifFullLoad) {
        this.ifFullLoad = ifFullLoad;
    }

    public String getIsbigcar() {
        return isbigcar;
    }

    public void setIsbigcar(String isbigcar) {
        this.isbigcar = isbigcar;
    }

    public BigDecimal getLampHigh() {
        return lampHigh;
    }

    public void setLampHigh(BigDecimal lampHigh) {
        this.lampHigh = lampHigh;
    }

    public String getCarNew() {
        return carNew;
    }

    public void setCarNew(String carNew) {
        this.carNew = carNew;
    }

    public BigDecimal getFullWgt() {
        return fullWgt;
    }

    public void setFullWgt(BigDecimal fullWgt) {
        this.fullWgt = fullWgt;
    }

    public String getInspectMc() {
        return inspectMc;
    }

    public void setInspectMc(String inspectMc) {
        this.inspectMc = inspectMc;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(String inspectTime) {
        this.inspectTime = inspectTime;
    }

    public String getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(String inspectDate) {
        this.inspectDate = inspectDate;
    }

    public String getInspectNum() {
        return inspectNum;
    }

    public void setInspectNum(String inspectNum) {
        this.inspectNum = inspectNum;
    }

    public Integer getInspectTimes() {
        return inspectTimes;
    }

    public void setInspectTimes(Integer inspectTimes) {
        this.inspectTimes = inspectTimes;
    }

    public String getInspectJudgeName() {
        return inspectJudgeName;
    }

    public void setInspectJudgeName(String inspectJudgeName) {
        this.inspectJudgeName = inspectJudgeName;
    }

    public String getFactoryNo() {
        return factoryNo;
    }

    public void setFactoryNo(String factoryNo) {
        this.factoryNo = factoryNo;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getFirstInspect() {
        return firstInspect;
    }

    public void setFirstInspect(String firstInspect) {
        this.firstInspect = firstInspect;
    }

    public String getInspectJudge() {
        return inspectJudge;
    }

    public void setInspectJudge(String inspectJudge) {
        this.inspectJudge = inspectJudge;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLicenseClr() {
        return licenseClr;
    }

    public void setLicenseClr(String licenseClr) {
        this.licenseClr = licenseClr;
    }

    public String getYingyunNo() {
        return yingyunNo;
    }

    public void setYingyunNo(String yingyunNo) {
        this.yingyunNo = yingyunNo;
    }

    public String getPowerNo() {
        return powerNo;
    }

    public void setPowerNo(String powerNo) {
        this.powerNo = powerNo;
    }

    public String getUnderpanNo() {
        return underpanNo;
    }

    public void setUnderpanNo(String underpanNo) {
        this.underpanNo = underpanNo;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getCarKind() {
        return carKind;
    }

    public void setCarKind(String carKind) {
        this.carKind = carKind;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getRunDate() {
        return runDate;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate;
    }

    public String getCarSeat() {
        return carSeat;
    }

    public void setCarSeat(String carSeat) {
        this.carSeat = carSeat;
    }

    public String getOiltypeMc() {
        return oiltypeMc;
    }

    public void setOiltypeMc(String oiltypeMc) {
        this.oiltypeMc = oiltypeMc;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getPopedom() {
        return popedom;
    }

    public void setPopedom(String popedom) {
        this.popedom = popedom;
    }

    public String getDrvtypeName() {
        return drvtypeName;
    }

    public void setDrvtypeName(String drvtypeName) {
        this.drvtypeName = drvtypeName;
    }

    public String getFrontLamp() {
        return frontLamp;
    }

    public void setFrontLamp(String frontLamp) {
        this.frontLamp = frontLamp;
    }

    public Integer getAxisNum() {
        return axisNum;
    }

    public void setAxisNum(Integer axisNum) {
        this.axisNum = axisNum;
    }

    public String getDrvAxis() {
        return drvAxis;
    }

    public void setDrvAxis(String drvAxis) {
        this.drvAxis = drvAxis;
    }

    public String getHandbrkAxis() {
        return handbrkAxis;
    }

    public void setHandbrkAxis(String handbrkAxis) {
        this.handbrkAxis = handbrkAxis;
    }

    public String getIsroad() {
        return isroad;
    }

    public void setIsroad(String isroad) {
        this.isroad = isroad;
    }

    public String getChecker1person() {
        return checker1person;
    }

    public void setChecker1person(String checker1person) {
        this.checker1person = checker1person;
    }

    public String getChecker2person() {
        return checker2person;
    }

    public void setChecker2person(String checker2person) {
        this.checker2person = checker2person;
    }

    public String getChecker3person() {
        return checker3person;
    }

    public void setChecker3person(String checker3person) {
        this.checker3person = checker3person;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public Integer getHmoIndephi() {
        return hmoIndephi;
    }

    public void setHmoIndephi(Integer hmoIndephi) {
        this.hmoIndephi = hmoIndephi;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Integer getHmoTurbo() {
        return hmoTurbo;
    }

    public void setHmoTurbo(Integer hmoTurbo) {
        this.hmoTurbo = hmoTurbo;
    }

    public String getChengyun() {
        return chengyun;
    }

    public void setChengyun(String chengyun) {
        this.chengyun = chengyun;
    }

    public String getDianpen() {
        return dianpen;
    }

    public void setDianpen(String dianpen) {
        this.dianpen = dianpen;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getInspect() {
        return inspect;
    }

    public void setInspect(String inspect) {
        this.inspect = inspect;
    }

    public Integer getIsVisa() {
        return isVisa;
    }

    public void setIsVisa(Integer isVisa) {
        this.isVisa = isVisa;
    }

    public Integer getSyncDsFlag() {
        return syncDsFlag;
    }

    public void setSyncDsFlag(Integer syncDsFlag) {
        this.syncDsFlag = syncDsFlag;
    }

    public Integer getFileFlag() {
        return fileFlag;
    }

    public void setFileFlag(Integer fileFlag) {
        this.fileFlag = fileFlag;
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

    public String getDetectSn() {
        return detectSn;
    }

    public void setDetectSn(String detectSn) {
        this.detectSn = detectSn;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Override
    protected Serializable pkVal() {
        return this.testId;
    }

    @Override
    public String toString() {
        return "Inspect{" +
        "testId=" + testId +
        ", vehicleId=" + vehicleId +
        ", entId=" + entId +
        ", workStationId=" + workStationId +
        ", fixRecId=" + fixRecId +
        ", carFatyId=" + carFatyId +
        ", ifFullLoad=" + ifFullLoad +
        ", isbigcar=" + isbigcar +
        ", lampHigh=" + lampHigh +
        ", carNew=" + carNew +
        ", fullWgt=" + fullWgt +
        ", inspectMc=" + inspectMc +
        ", startTime=" + startTime +
        ", inspectTime=" + inspectTime +
        ", inspectDate=" + inspectDate +
        ", inspectNum=" + inspectNum +
        ", inspectTimes=" + inspectTimes +
        ", inspectJudgeName=" + inspectJudgeName +
        ", factoryNo=" + factoryNo +
        ", license=" + license +
        ", firstInspect=" + firstInspect +
        ", inspectJudge=" + inspectJudge +
        ", grade=" + grade +
        ", licenseClr=" + licenseClr +
        ", yingyunNo=" + yingyunNo +
        ", powerNo=" + powerNo +
        ", underpanNo=" + underpanNo +
        ", unitName=" + unitName +
        ", certificateNo=" + certificateNo +
        ", factoryName=" + factoryName +
        ", carKind=" + carKind +
        ", outDate=" + outDate +
        ", runDate=" + runDate +
        ", carSeat=" + carSeat +
        ", oiltypeMc=" + oiltypeMc +
        ", carColor=" + carColor +
        ", popedom=" + popedom +
        ", drvtypeName=" + drvtypeName +
        ", frontLamp=" + frontLamp +
        ", axisNum=" + axisNum +
        ", drvAxis=" + drvAxis +
        ", handbrkAxis=" + handbrkAxis +
        ", isroad=" + isroad +
        ", checker1person=" + checker1person +
        ", checker2person=" + checker2person +
        ", checker3person=" + checker3person +
        ", leaderName=" + leaderName +
        ", hmoIndephi=" + hmoIndephi +
        ", createby=" + createby +
        ", hmoTurbo=" + hmoTurbo +
        ", chengyun=" + chengyun +
        ", dianpen=" + dianpen +
        ", organ=" + organ +
        ", modifydate=" + modifydate +
        ", inspect=" + inspect +
        ", isVisa=" + isVisa +
        ", syncDsFlag=" + syncDsFlag +
        ", fileFlag=" + fileFlag +
        ", syncYsNewFlag=" + syncYsNewFlag +
        ", syncYsEditFlag=" + syncYsEditFlag +
        ", syncYsDelFlag=" + syncYsDelFlag +
        ", syncPlatEditFlag=" + syncPlatEditFlag +
        ", syncPlatDelFlag=" + syncPlatDelFlag +
        ", syncPlatNewFlag=" + syncPlatNewFlag +
        ", detectSn=" + detectSn +
        ", checkcode=" + checkcode +
        "}";
    }
}
