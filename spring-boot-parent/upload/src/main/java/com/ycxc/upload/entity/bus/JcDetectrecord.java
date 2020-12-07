package com.ycxc.upload.entity.bus;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ycxc.upload.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 检测记录单
 * </p>
 *
 * @author hack2003
 * @since 2019-07-26
 */
public class JcDetectrecord extends BaseEntity<JcDetectrecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 检测流水号  检测流水号编码规则：行政区划代码（6位）+检验检测机
            构代码（3位）+年月日（YYYYMMDD）+检验序号（4位），其中“检
            验序号”按当日检车数量的次序
     */
    @TableId("detectSn")
    private String detectSn;

    /**
     * 检验检测机构唯一
            编码,检验检测机构唯一编码由行政区划代码（6位）和检验检测
            机构代码（3位）组成
     */
    @TableField("dsId")
    private String dsId;

    /**
     * 检验检测机构名称
     */
    @TableField("dsName")
    private String dsName;

    /**
     * 检测类别:1-- 技术等级评定;4-- 二级维护竣工质量检验;5-- 汽车大修竣工质量检验;9-- 其他检测
     */
    @TableField("detectType")
    private Integer detectType;

    /**
     * 检测日期: YYYY-MM-DD
            hh:mm:ss
     */
    @TableField("detectDate")
    private Date detectDate;

    /**
     * 委托人
     */
    @TableField("client")
    private String client;

    /**
     * 车牌号码
     */
    @TableField("vehicleNo")
    private String vehicleNo;

    /**
     * 车牌颜色代码
     */
    @TableField("plateColorCode")
    private Integer plateColorCode;

    /**
     * 车辆识别代码
     */
    @TableField("vinNo")
    private String vinNo;

    /**
     * 厂牌型号：参照JT/T
            697.7
     */
    @TableField("vehicleBrandModel")
    private String vehicleBrandModel;

    /**
     * 注册日期: YYYYMMDD
     */
    @TableField("registDate")
    private Date registDate;

    /**
     * 车辆类型，参照GA 24.4
     */
    @TableField("vehicleType")
    private String vehicleType;

    /**
     * 单车外廓尺寸，格式：
            长×宽×高，单位：mm
     */
    @TableField("overallSize")
    private String overallSize;

    /**
     * 发动机号码
     */
    @TableField("engineNo")
    private String engineNo;

    /**
     * 行驶总里程，单位km
     */
    @TableField("travelMileage")
    private Integer travelMileage;

    /**
     * 燃油类别，参照JT/T697.7
     */
    @TableField("fuelType")
    private String fuelType;

    /**
     * 转向轴数，单位：轴
     */
    @TableField("steeringAxleAmount")
    private Integer steeringAxleAmount;

    /**
     * 检测线别：
            规定值：大写英文字母
     */
    @TableField("detectLine")
    private String detectLine;

    /**
     * 业务类型：
            规定值：申请、在用
     */
    @TableField("busiType")
    private String busiType;

    /**
     * 道路运输证号
     */
    @TableField("transCertificateCode")
    private String transCertificateCode;

    /**
     * 挂车牌照号码
     */
    @TableField("trailerVehicleNo")
    private String trailerVehicleNo;

    /**
     * 挂车类型，参照GB/T
            3730.2
     */
    @TableField("trailerVehicleType")
    private String trailerVehicleType;

    /**
     * 出厂日期
     */
    @TableField("productionDate")
    private Date productionDate;

    /**
     * 车身颜色，见附录B.2
     */
    @TableField("vehicleBodyColor")
    private String vehicleBodyColor;

    /**
     * 驱动型式，如：4×2后
            驱
     */
    @TableField("driveType")
    private String driveType;

    /**
     * 车辆悬架形式，参照
            JT/T 697.7
     */
    @TableField("vehicleSuspensionForm")
    private String vehicleSuspensionForm;

    /**
     * 挂车轴数，单位：轴
     */
    @TableField("trailerVehicleAxleAmount")
    private Integer trailerVehicleAxleAmount;

    /**
     * 发动机型号
     */
    @TableField("engineModel")
    private String engineModel;

    /**
     * 压燃式发动机额定功
            率，单位：kW
     */
    @TableField("compressIgnitEnginePower")
    private BigDecimal compressIgnitEnginePower;

    /**
     * 点燃式额定扭矩
     */
    @TableField("ratedTorque")
    private BigDecimal ratedTorque;

    /**
     * 点燃式额定转速
     */
    @TableField("ratedSpeedOfIgnit")
    private BigDecimal ratedSpeedOfIgnit;

    /**
     * 驱动轮轮胎规格型号
     */
    @TableField("driveWheelModel")
    private String driveWheelModel;

    /**
     * 总质量，单位：kg
     */
    @TableField("totalWeight")
    private BigDecimal totalWeight;

    /**
     * 车高，单位：mm
     */
    @TableField("vehicleHeight")
    private BigDecimal vehicleHeight;

    /**
     * 前轮距，单位：mm
     */
    @TableField("frontTrack")
    private BigDecimal frontTrack;

    /**
     * 客车车长，单位：mm
     */
    @TableField("vehicleLength")
    private BigDecimal vehicleLength;

    /**
     * 客车类型与等级，参照
            JT/T 697.7
     */
    @TableField("busTypeLevel")
    private BigDecimal busTypeLevel;

    /**
     * 货车车身型式
     */
    @TableField("truckBodyType")
    private String truckBodyType;

    /**
     * 驱动轴数，单位：轴
     */
    @TableField("driveAxleAmount")
    private Integer driveAxleAmount;

    /**
     * 驱动轴空载质量，单
            位:kg
     */
    @TableField("driveAxleLoadMass")
    private BigDecimal driveAxleLoadMass;

    /**
     * 牵引车满载总质量（最
            大允许总质量），单
            位:kg
     */
    @TableField("totalWeightOfTractor")
    private BigDecimal totalWeightOfTractor;

    /**
     * 并装轴形式，如：并装
            双轴、并装三轴等
     */
    @TableField("shaftForm")
    private String shaftForm;

    /**
     * 前照灯制，规定值：二、
            四
     */
    @TableField("lampSystem")
    private String lampSystem;

    /**
     * 座位（铺）数，单位：
            位，客车必填，货车非
            必填
     */
    @TableField("seatCount")
    private Integer seatCount;

    /**
     * 单车（主车）轴数
     */
    @TableField("mainVehicleAxleAmount")
    private Integer mainVehicleAxleAmount;

    /**
     * 挂车外廓尺寸，格式：
            长×宽×高，单位：mm
     */
    @TableField("overallSizeTrailer")
    private String overallSizeTrailer;

    /**
     * 远光束能否单独调整
     */
    @TableField("farLightCanAdjust")
    private String farLightCanAdjust;

    /**
     * 单车车厢栏板高度，单
            位：mm
     */
    @TableField("carriageSsideboardHeight")
    private BigDecimal carriageSsideboardHeight;

    /**
     * 驻车轴，用数字表示，
            作用在多轴时，各驻车
            轴数用“,”分开
     */
    @TableField("parkAxle")
    private String parkAxle;

    /**
     * 挂车车厢栏板高度，单
            位：mm
     */
    @TableField("ssideboardHeightTrailer")
    private BigDecimal ssideboardHeightTrailer;

    /**
     * 总检次数，单位：次
     */
    @TableField("detectTotalCount")
    private BigDecimal detectTotalCount;

    @TableField("createTime")
    private Date createTime;

    /**
     * GA24标准所列的号牌种类名称
     */
    @TableField("licensePlateType")
    private String licensePlateType;

    /**
     * MN类型
     */
    @TableField("mn")
    private String mn;

    /**
     * 车辆系列
     */
    @TableField("vehicleSeries")
    private String vehicleSeries;

    /**
     * 所属辖区
     */
    @TableField("popedom")
    private String popedom;

    /**
     * 底盘类型
     */
    @TableField("chassisType")
    private String chassisType;

    /**
     * 是否新车，限定值[是、否] 
     */
    @TableField("isNewCar")
    private String isNewCar;

    /**
     * 车辆使用性质
     */
    @TableField("vehicleUse")
    private String vehicleUse;

    /**
     * 是否重型车，限定值[是、否]
     */
    @TableField("isHeavy")
    private String isHeavy;

    /**
     * 是否乘用车，限定值[是、否]
     */
    @TableField("isPassenger")
    private String isPassenger;

    /**
     * 制动方式，限定值[液压、气压]
     */
    @TableField("brakeType")
    private String brakeType;

    /**
     * 驱动起始轴位
     */
    @TableField("driveStart")
    private Integer driveStart;

    /**
     * 制造厂家
     */
    @TableField("manufacturer")
    private String manufacturer;

    /**
     * 国别
     */
    @TableField("country")
    private String country;

    /**
     * 乘员数量
     */
    @TableField("passengersNum")
    private String passengersNum;

    /**
     * 是否双后桥，固定值[是、否]
     */
    @TableField("isDoubleBuild")
    private String isDoubleBuild;

    /**
     * 轴距
     */
    @TableField("wheelbase")
    private Integer wheelbase;

    /**
     * 质量比(总质量/整备质量)
     */
    @TableField("qualityPercentage")
    private String qualityPercentage;

    /**
     * 登陆员
     */
    @TableField("loginMember")
    private String loginMember;

    /**
     * 检测上线时间
     */
    @TableField("onlineTime")
    private Date onlineTime;

    /**
     * 检测下线时间
     */
    @TableField("offlineTime")
    private Date offlineTime;

    /**
     * 天气状况
     */
    @TableField("weather")
    private String weather;

    /**
     * 大气压力
     */
    @TableField("atmosphere")
    private String atmosphere;

    /**
     * 环境温度
     */
    @TableField("temperature")
    private String temperature;

    /**
     * 相对湿度
     */
    @TableField("humidity")
    private String humidity;

    /**
     * 饱和蒸汽压
     */
    @TableField("pressure")
    private String pressure;

    /**
     * 是否路试，限定值[路试、台试]
     */
    @TableField("isRoadTest")
    private String isRoadTest;

    /**
     * 是否空载，限定值[空载、满载]
     */
    @TableField("isEmptyLoad")
    private String isEmptyLoad;

    /**
     * 引车员
     */
    @TableField("leder")
    private String leder;

    /**
     * 外检员
     */
    @TableField("outsider")
    private String outsider;

    /**
     * 底检员
     */
    @TableField("bottomChecker")
    private String bottomChecker;

    /**
     * 授权签字人
     */
    @TableField("authorizationer")
    private String authorizationer;

    /**
     * 评定技术等级
     */
    @TableField("evaluate")
    private String evaluate;

    /**
     * 是否打印，限定值[是、否]
     */
    @TableField("isprint")
    private String isprint;

    /**
     * 是否签证，限定值[是、否]
     */
    @TableField("isvisa")
    private String isvisa;

    /**
     * 是否上传，0：未上传，1：已上传
     */
    @TableField("status")
    private Integer status;

    /**
     * 核定满载人员数，参照JT/T 697.7
     */
    @TableField("ratifiedLoadCapacity")
    private Integer ratifiedLoadCapacity;

    /**
     * 合格证号
     */
    @TableField("issue")
    private String issue;

    /**
     * 不合格项汇总
     */
    @TableField("unqualified")
    private String unqualified;


    public String getDetectSn() {
        return detectSn;
    }

    public void setDetectSn(String detectSn) {
        this.detectSn = detectSn;
    }

    public String getDsId() {
        return dsId;
    }

    public void setDsId(String dsId) {
        this.dsId = dsId;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public Integer getDetectType() {
        return detectType;
    }

    public void setDetectType(Integer detectType) {
        this.detectType = detectType;
    }

    public Date getDetectDate() {
        return detectDate;
    }

    public void setDetectDate(Date detectDate) {
        this.detectDate = detectDate;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Integer getPlateColorCode() {
        return plateColorCode;
    }

    public void setPlateColorCode(Integer plateColorCode) {
        this.plateColorCode = plateColorCode;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getVehicleBrandModel() {
        return vehicleBrandModel;
    }

    public void setVehicleBrandModel(String vehicleBrandModel) {
        this.vehicleBrandModel = vehicleBrandModel;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getOverallSize() {
        return overallSize;
    }

    public void setOverallSize(String overallSize) {
        this.overallSize = overallSize;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public Integer getTravelMileage() {
        return travelMileage;
    }

    public void setTravelMileage(Integer travelMileage) {
        this.travelMileage = travelMileage;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getSteeringAxleAmount() {
        return steeringAxleAmount;
    }

    public void setSteeringAxleAmount(Integer steeringAxleAmount) {
        this.steeringAxleAmount = steeringAxleAmount;
    }

    public String getDetectLine() {
        return detectLine;
    }

    public void setDetectLine(String detectLine) {
        this.detectLine = detectLine;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getTransCertificateCode() {
        return transCertificateCode;
    }

    public void setTransCertificateCode(String transCertificateCode) {
        this.transCertificateCode = transCertificateCode;
    }

    public String getTrailerVehicleNo() {
        return trailerVehicleNo;
    }

    public void setTrailerVehicleNo(String trailerVehicleNo) {
        this.trailerVehicleNo = trailerVehicleNo;
    }

    public String getTrailerVehicleType() {
        return trailerVehicleType;
    }

    public void setTrailerVehicleType(String trailerVehicleType) {
        this.trailerVehicleType = trailerVehicleType;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getVehicleBodyColor() {
        return vehicleBodyColor;
    }

    public void setVehicleBodyColor(String vehicleBodyColor) {
        this.vehicleBodyColor = vehicleBodyColor;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getVehicleSuspensionForm() {
        return vehicleSuspensionForm;
    }

    public void setVehicleSuspensionForm(String vehicleSuspensionForm) {
        this.vehicleSuspensionForm = vehicleSuspensionForm;
    }

    public Integer getTrailerVehicleAxleAmount() {
        return trailerVehicleAxleAmount;
    }

    public void setTrailerVehicleAxleAmount(Integer trailerVehicleAxleAmount) {
        this.trailerVehicleAxleAmount = trailerVehicleAxleAmount;
    }

    public String getEngineModel() {
        return engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }

    public BigDecimal getCompressIgnitEnginePower() {
        return compressIgnitEnginePower;
    }

    public void setCompressIgnitEnginePower(BigDecimal compressIgnitEnginePower) {
        this.compressIgnitEnginePower = compressIgnitEnginePower;
    }

    public BigDecimal getRatedTorque() {
        return ratedTorque;
    }

    public void setRatedTorque(BigDecimal ratedTorque) {
        this.ratedTorque = ratedTorque;
    }

    public BigDecimal getRatedSpeedOfIgnit() {
        return ratedSpeedOfIgnit;
    }

    public void setRatedSpeedOfIgnit(BigDecimal ratedSpeedOfIgnit) {
        this.ratedSpeedOfIgnit = ratedSpeedOfIgnit;
    }

    public String getDriveWheelModel() {
        return driveWheelModel;
    }

    public void setDriveWheelModel(String driveWheelModel) {
        this.driveWheelModel = driveWheelModel;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getVehicleHeight() {
        return vehicleHeight;
    }

    public void setVehicleHeight(BigDecimal vehicleHeight) {
        this.vehicleHeight = vehicleHeight;
    }

    public BigDecimal getFrontTrack() {
        return frontTrack;
    }

    public void setFrontTrack(BigDecimal frontTrack) {
        this.frontTrack = frontTrack;
    }

    public BigDecimal getVehicleLength() {
        return vehicleLength;
    }

    public void setVehicleLength(BigDecimal vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    public BigDecimal getBusTypeLevel() {
        return busTypeLevel;
    }

    public void setBusTypeLevel(BigDecimal busTypeLevel) {
        this.busTypeLevel = busTypeLevel;
    }

    public String getTruckBodyType() {
        return truckBodyType;
    }

    public void setTruckBodyType(String truckBodyType) {
        this.truckBodyType = truckBodyType;
    }

    public Integer getDriveAxleAmount() {
        return driveAxleAmount;
    }

    public void setDriveAxleAmount(Integer driveAxleAmount) {
        this.driveAxleAmount = driveAxleAmount;
    }

    public BigDecimal getDriveAxleLoadMass() {
        return driveAxleLoadMass;
    }

    public void setDriveAxleLoadMass(BigDecimal driveAxleLoadMass) {
        this.driveAxleLoadMass = driveAxleLoadMass;
    }

    public BigDecimal getTotalWeightOfTractor() {
        return totalWeightOfTractor;
    }

    public void setTotalWeightOfTractor(BigDecimal totalWeightOfTractor) {
        this.totalWeightOfTractor = totalWeightOfTractor;
    }

    public String getShaftForm() {
        return shaftForm;
    }

    public void setShaftForm(String shaftForm) {
        this.shaftForm = shaftForm;
    }

    public String getLampSystem() {
        return lampSystem;
    }

    public void setLampSystem(String lampSystem) {
        this.lampSystem = lampSystem;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Integer getMainVehicleAxleAmount() {
        return mainVehicleAxleAmount;
    }

    public void setMainVehicleAxleAmount(Integer mainVehicleAxleAmount) {
        this.mainVehicleAxleAmount = mainVehicleAxleAmount;
    }

    public String getOverallSizeTrailer() {
        return overallSizeTrailer;
    }

    public void setOverallSizeTrailer(String overallSizeTrailer) {
        this.overallSizeTrailer = overallSizeTrailer;
    }

    public String getFarLightCanAdjust() {
        return farLightCanAdjust;
    }

    public void setFarLightCanAdjust(String farLightCanAdjust) {
        this.farLightCanAdjust = farLightCanAdjust;
    }

    public BigDecimal getCarriageSsideboardHeight() {
        return carriageSsideboardHeight;
    }

    public void setCarriageSsideboardHeight(BigDecimal carriageSsideboardHeight) {
        this.carriageSsideboardHeight = carriageSsideboardHeight;
    }

    public String getParkAxle() {
        return parkAxle;
    }

    public void setParkAxle(String parkAxle) {
        this.parkAxle = parkAxle;
    }

    public BigDecimal getSsideboardHeightTrailer() {
        return ssideboardHeightTrailer;
    }

    public void setSsideboardHeightTrailer(BigDecimal ssideboardHeightTrailer) {
        this.ssideboardHeightTrailer = ssideboardHeightTrailer;
    }

    public BigDecimal getDetectTotalCount() {
        return detectTotalCount;
    }

    public void setDetectTotalCount(BigDecimal detectTotalCount) {
        this.detectTotalCount = detectTotalCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLicensePlateType() {
        return licensePlateType;
    }

    public void setLicensePlateType(String licensePlateType) {
        this.licensePlateType = licensePlateType;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getVehicleSeries() {
        return vehicleSeries;
    }

    public void setVehicleSeries(String vehicleSeries) {
        this.vehicleSeries = vehicleSeries;
    }

    public String getPopedom() {
        return popedom;
    }

    public void setPopedom(String popedom) {
        this.popedom = popedom;
    }

    public String getChassisType() {
        return chassisType;
    }

    public void setChassisType(String chassisType) {
        this.chassisType = chassisType;
    }

    public String getIsNewCar() {
        return isNewCar;
    }

    public void setIsNewCar(String isNewCar) {
        this.isNewCar = isNewCar;
    }

    public String getVehicleUse() {
        return vehicleUse;
    }

    public void setVehicleUse(String vehicleUse) {
        this.vehicleUse = vehicleUse;
    }

    public String getIsHeavy() {
        return isHeavy;
    }

    public void setIsHeavy(String isHeavy) {
        this.isHeavy = isHeavy;
    }

    public String getIsPassenger() {
        return isPassenger;
    }

    public void setIsPassenger(String isPassenger) {
        this.isPassenger = isPassenger;
    }

    public String getBrakeType() {
        return brakeType;
    }

    public void setBrakeType(String brakeType) {
        this.brakeType = brakeType;
    }

    public Integer getDriveStart() {
        return driveStart;
    }

    public void setDriveStart(Integer driveStart) {
        this.driveStart = driveStart;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassengersNum() {
        return passengersNum;
    }

    public void setPassengersNum(String passengersNum) {
        this.passengersNum = passengersNum;
    }

    public String getIsDoubleBuild() {
        return isDoubleBuild;
    }

    public void setIsDoubleBuild(String isDoubleBuild) {
        this.isDoubleBuild = isDoubleBuild;
    }

    public Integer getWheelbase() {
        return wheelbase;
    }

    public void setWheelbase(Integer wheelbase) {
        this.wheelbase = wheelbase;
    }

    public String getQualityPercentage() {
        return qualityPercentage;
    }

    public void setQualityPercentage(String qualityPercentage) {
        this.qualityPercentage = qualityPercentage;
    }

    public String getLoginMember() {
        return loginMember;
    }

    public void setLoginMember(String loginMember) {
        this.loginMember = loginMember;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(String atmosphere) {
        this.atmosphere = atmosphere;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getIsRoadTest() {
        return isRoadTest;
    }

    public void setIsRoadTest(String isRoadTest) {
        this.isRoadTest = isRoadTest;
    }

    public String getIsEmptyLoad() {
        return isEmptyLoad;
    }

    public void setIsEmptyLoad(String isEmptyLoad) {
        this.isEmptyLoad = isEmptyLoad;
    }

    public String getLeder() {
        return leder;
    }

    public void setLeder(String leder) {
        this.leder = leder;
    }

    public String getOutsider() {
        return outsider;
    }

    public void setOutsider(String outsider) {
        this.outsider = outsider;
    }

    public String getBottomChecker() {
        return bottomChecker;
    }

    public void setBottomChecker(String bottomChecker) {
        this.bottomChecker = bottomChecker;
    }

    public String getAuthorizationer() {
        return authorizationer;
    }

    public void setAuthorizationer(String authorizationer) {
        this.authorizationer = authorizationer;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getIsprint() {
        return isprint;
    }

    public void setIsprint(String isprint) {
        this.isprint = isprint;
    }

    public String getIsvisa() {
        return isvisa;
    }

    public void setIsvisa(String isvisa) {
        this.isvisa = isvisa;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRatifiedLoadCapacity() {
        return ratifiedLoadCapacity;
    }

    public void setRatifiedLoadCapacity(Integer ratifiedLoadCapacity) {
        this.ratifiedLoadCapacity = ratifiedLoadCapacity;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getUnqualified() {
        return unqualified;
    }

    public void setUnqualified(String unqualified) {
        this.unqualified = unqualified;
    }

    @Override
    protected Serializable pkVal() {
        return this.detectSn;
    }

    @Override
    public String toString() {
        return "JcDetectrecord{" +
        "detectSn=" + detectSn +
        ", dsId=" + dsId +
        ", dsName=" + dsName +
        ", detectType=" + detectType +
        ", detectDate=" + detectDate +
        ", client=" + client +
        ", vehicleNo=" + vehicleNo +
        ", plateColorCode=" + plateColorCode +
        ", vinNo=" + vinNo +
        ", vehicleBrandModel=" + vehicleBrandModel +
        ", registDate=" + registDate +
        ", vehicleType=" + vehicleType +
        ", overallSize=" + overallSize +
        ", engineNo=" + engineNo +
        ", travelMileage=" + travelMileage +
        ", fuelType=" + fuelType +
        ", steeringAxleAmount=" + steeringAxleAmount +
        ", detectLine=" + detectLine +
        ", busiType=" + busiType +
        ", transCertificateCode=" + transCertificateCode +
        ", trailerVehicleNo=" + trailerVehicleNo +
        ", trailerVehicleType=" + trailerVehicleType +
        ", productionDate=" + productionDate +
        ", vehicleBodyColor=" + vehicleBodyColor +
        ", driveType=" + driveType +
        ", vehicleSuspensionForm=" + vehicleSuspensionForm +
        ", trailerVehicleAxleAmount=" + trailerVehicleAxleAmount +
        ", engineModel=" + engineModel +
        ", compressIgnitEnginePower=" + compressIgnitEnginePower +
        ", ratedTorque=" + ratedTorque +
        ", ratedSpeedOfIgnit=" + ratedSpeedOfIgnit +
        ", driveWheelModel=" + driveWheelModel +
        ", totalWeight=" + totalWeight +
        ", vehicleHeight=" + vehicleHeight +
        ", frontTrack=" + frontTrack +
        ", vehicleLength=" + vehicleLength +
        ", busTypeLevel=" + busTypeLevel +
        ", truckBodyType=" + truckBodyType +
        ", driveAxleAmount=" + driveAxleAmount +
        ", driveAxleLoadMass=" + driveAxleLoadMass +
        ", totalWeightOfTractor=" + totalWeightOfTractor +
        ", shaftForm=" + shaftForm +
        ", lampSystem=" + lampSystem +
        ", seatCount=" + seatCount +
        ", mainVehicleAxleAmount=" + mainVehicleAxleAmount +
        ", overallSizeTrailer=" + overallSizeTrailer +
        ", farLightCanAdjust=" + farLightCanAdjust +
        ", carriageSsideboardHeight=" + carriageSsideboardHeight +
        ", parkAxle=" + parkAxle +
        ", ssideboardHeightTrailer=" + ssideboardHeightTrailer +
        ", detectTotalCount=" + detectTotalCount +
        ", createTime=" + createTime +
        ", licensePlateType=" + licensePlateType +
        ", mn=" + mn +
        ", vehicleSeries=" + vehicleSeries +
        ", popedom=" + popedom +
        ", chassisType=" + chassisType +
        ", isNewCar=" + isNewCar +
        ", vehicleUse=" + vehicleUse +
        ", isHeavy=" + isHeavy +
        ", isPassenger=" + isPassenger +
        ", brakeType=" + brakeType +
        ", driveStart=" + driveStart +
        ", manufacturer=" + manufacturer +
        ", country=" + country +
        ", passengersNum=" + passengersNum +
        ", isDoubleBuild=" + isDoubleBuild +
        ", wheelbase=" + wheelbase +
        ", qualityPercentage=" + qualityPercentage +
        ", loginMember=" + loginMember +
        ", onlineTime=" + onlineTime +
        ", offlineTime=" + offlineTime +
        ", weather=" + weather +
        ", atmosphere=" + atmosphere +
        ", temperature=" + temperature +
        ", humidity=" + humidity +
        ", pressure=" + pressure +
        ", isRoadTest=" + isRoadTest +
        ", isEmptyLoad=" + isEmptyLoad +
        ", leder=" + leder +
        ", outsider=" + outsider +
        ", bottomChecker=" + bottomChecker +
        ", authorizationer=" + authorizationer +
        ", evaluate=" + evaluate +
        ", isprint=" + isprint +
        ", isvisa=" + isvisa +
        ", status=" + status +
        ", ratifiedLoadCapacity=" + ratifiedLoadCapacity +
        ", issue=" + issue +
        ", unqualified=" + unqualified +
        "}";
    }
}
