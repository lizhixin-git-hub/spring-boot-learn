package com.ycxc.upload.service.upload.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ycxc.upload.controller.bean.response.ResponseDto;
import com.ycxc.upload.entity.bus.*;
import com.ycxc.upload.enums.EntEnums;
import com.ycxc.upload.common.utils.JsonUtil;
import com.ycxc.upload.entity.log.DcLog;
import com.ycxc.upload.enums.VehicleEnums;
import com.ycxc.upload.mapper.bas.*;
import com.ycxc.upload.entity.bas.*;
import com.ycxc.upload.mapper.bus.*;
import com.ycxc.upload.mapper.log.DcLogMapper;
import com.ycxc.upload.service.http.IHttpClientHelperService;
import com.ycxc.upload.service.upload.ISyncToDcService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("syncToDcService")
public class SyncToDcServiceImpl implements ISyncToDcService {

    @Value("${dc.login-interface-url}")
    String dc_login_interface_url;

    private EntMapper entMapper;

    private FixFactoryMapper fixFactoryMapper;

    private InspectSpotMapper inspectSpotMapper;

    private EntFixTypeMapper entFixTypeMapper;

    private VehicleTypeG24Mapper vehicleTypeG24Mapper;

    private VehicleCompanyMapper vehicleCompanyMapper;

    private ComScopeMapper comScopeMapper;

    private ComScopeTypeMapper comScopeTypeMapper;

    private DcLogMapper dcLogMapper;

    private VehicleMapper vehicleMapper;

    private InspectMapper inspectMapper;

    private InspectImgMapper inspectImgMapper;

    private JcImageMapper jcImageMapper;

    private DcUploadMapper dcUploadMapper;

    private WorkStationMapper workStationMapper;

    private EntVideoMapper entVideoMapper;

    private JcDetectsncreateMapper jcDetectsncreateMapper;

    private EntCostdetailMapper entCostdetailMapper;

    private IHttpClientHelperService httpClientHelperService;

    @Resource
    public void setEntMapper(EntMapper entMapper) {
        this.entMapper = entMapper;
    }

    @Resource
    public void setFixFactoryMapper(FixFactoryMapper fixFactoryMapper) {
        this.fixFactoryMapper = fixFactoryMapper;
    }

    @Resource
    public void setInspectSpotMapper(InspectSpotMapper inspectSpotMapper) {
        this.inspectSpotMapper = inspectSpotMapper;
    }

    @Resource
    public void setEntFixTypeMapper(EntFixTypeMapper entFixTypeMapper) {
        this.entFixTypeMapper = entFixTypeMapper;
    }

    @Resource
    public void setVehicleTypeG24Mapper(VehicleTypeG24Mapper vehicleTypeG24Mapper) {
        this.vehicleTypeG24Mapper = vehicleTypeG24Mapper;
    }

    @Resource
    public void setVehicleCompanyMapper(VehicleCompanyMapper vehicleCompanyMapper) {
        this.vehicleCompanyMapper = vehicleCompanyMapper;
    }

    @Resource
    public void setComScopeMapper(ComScopeMapper comScopeMapper) {
        this.comScopeMapper = comScopeMapper;
    }

    @Resource
    public void setComScopeTypeMapper(ComScopeTypeMapper comScopeTypeMapper) {
        this.comScopeTypeMapper = comScopeTypeMapper;
    }

    @Resource
    public void setDcLogMapper(DcLogMapper dcLogMapper) {
        this.dcLogMapper = dcLogMapper;
    }

    @Resource
    public void setVehicleMapper(VehicleMapper vehicleMapper) {
        this.vehicleMapper = vehicleMapper;
    }

    @Resource
    public void setInspectMapper(InspectMapper inspectMapper) {
        this.inspectMapper = inspectMapper;
    }

    @Resource
    public void setJcImageMapper(JcImageMapper jcImageMapper) {
        this.jcImageMapper = jcImageMapper;
    }

    @Resource
    public void setInspectImgMapper(InspectImgMapper inspectImgMapper) {
        this.inspectImgMapper = inspectImgMapper;
    }

    @Resource
    public void setDcUploadMapper(DcUploadMapper dcUploadMapper) {
        this.dcUploadMapper = dcUploadMapper;
    }

    @Resource
    public void setWorkStationMapper(WorkStationMapper workStationMapper) {
        this.workStationMapper = workStationMapper;
    }

    @Resource
    public void setEntVideoMapper(EntVideoMapper entVideoMapper) {
        this.entVideoMapper = entVideoMapper;
    }

    @Resource
    public void setJcDetectsncreateMapper(JcDetectsncreateMapper jcDetectsncreateMapper) {
        this.jcDetectsncreateMapper = jcDetectsncreateMapper;
    }

    @Resource
    public void setEntCostdetailMapper(EntCostdetailMapper entCostdetailMapper) {
        this.entCostdetailMapper = entCostdetailMapper;
    }

    @Autowired
    public void setHttpClientHelperService(IHttpClientHelperService httpClientHelperService) {
        this.httpClientHelperService = httpClientHelperService;
    }

    @Value("${dc.ent-url}")
    private String dcEntUrl;

    @Value("${dc.inspect-url}")
    private String dcInspectUrl;

    @Value("${dc.login.user-name}")
    private String username;

    @Value("${dc.login.password}")
    private String password;

    @Override
    public void uploadFixAndInspectEnt() {
        //Integer count = dcLogMapper.selectCount(new QueryWrapper<DcLog>().eq("type", 0));
        QueryWrapper<Ent> queryWrapper = new QueryWrapper<>();
        /*if (count == null || count == 0) {
            queryWrapper.le("create_time", DateUtil.format(DateUtil.endOfDay(new Date()), "yyyy-MM-dd HH:mm:ss"));
        } else {
            queryWrapper.ge("create_time", DateUtil.format(DateUtil.beginOfDay(new Date()), "yyyy-MM-dd HH:mm:ss"))
                    .le("create_time", DateUtil.format(DateUtil.endOfDay(new Date()), "yyyy-MM-dd HH:mm:ss"));
        }*/
        List<Ent> entList = entMapper.selectList(queryWrapper.in("ent_id",new Integer[]{1808,1850,1044902,1044903}));
        entList.forEach(ent -> {
            String jsonData = null;
            DcLog dcLog = new DcLog();
            dcLog.setUploadDataPrimaryKey(ent.getEntId());
            try {
                FixFactory fixFactory = fixFactoryMapper.selectOne(new QueryWrapper<FixFactory>().eq("ent_id", ent.getEntId()));
                InspectSpot inspectSpot = inspectSpotMapper.selectOne(new QueryWrapper<InspectSpot>().eq("ent_id", ent.getEntId()));
                List<EntFixType> entFixTypes = entFixTypeMapper.selectList(new QueryWrapper<EntFixType>().eq("ent_id", ent.getEntId()));
                Map<String, Object> entMap = new HashMap<>();
                entMap.put("enterpriseName", ent.getEntName());// 企业全称
                entMap.put("enterpriseJcShortName", ent.getShortName());// 企业简称
                entMap.put("enterpriseTypeId", ent.getEntType());// 企业类型 1维修企业，2检测企业，3运输企业
                entMap.put("enterpriseDistrict", StringUtils.isBlank(ent.getDistrict()) ? "450101" : ent.getDistrict());// 企业行政区域
                entMap.put("enterpriseRegion", StringUtils.isBlank(ent.getDistrict()) ? "450101" : ent.getDistrict());// 企业所在区域
                entMap.put("enterpriseBusinessCertificate", ent.getLicenceNo());// 企业经营许可证
                entMap.put("enterpriseEconomicCategory", EntEnums.EconomyKind.getDesc(ent.getEconomyKind()));// 经济性质
                entMap.put("enterpriseOwner", ent.getLegalPerson());// 企业负责人（法人代表）
                entMap.put("enterpriseOwnerPhone", ent.getPhone());// 企业负责人电话
                entMap.put("enterpriseLinkName", ent.getContactPerson());// 企业联系人
                entMap.put("enterpriseLinkMobile", ent.getPhone());// 企业联系电话
                entMap.put("enterpriseBusinessPhone", ent.getPhone());// 企业业务电话
                entMap.put("enterprisePostalcode", ent.getZip());// 企业邮政编码
                entMap.put("enterpriseOperatingState", "01");// 企业经营状态
                entMap.put("enterpriseJcBusinessS", ent.getStartTime() == null ? null : DateFormatUtils.format(ent.getStartTime(), "HH:mm"));// 营业开始时间
                entMap.put("enterpriseJcBusinessE", ent.getEndTime() == null ? null : DateFormatUtils.format(ent.getEndTime(), "HH:mm"));// 营业结束时间
                entMap.put("enterpriseJcPhotoMode", ent.getPhotoType());// 拍照模式（1自动2手动3手动&自动4不需要拍照）
                entMap.put("enterpriseIsAudit", 0);// 未备案1已备案 -1被拒绝,2重新申请
                entMap.put("createTime", ent.getCreateTime() == null ? new Date().getTime() : ent.getCreateTime().getTime());// 创建时间
                entMap.put("updateTime", new Date().getTime());// 更新时间
                entMap.put("enterpriseProvince", StringUtils.isBlank(ent.getProvince()) ? "450000" : ent.getProvince());// 所在省编码
                entMap.put("enterpriseCity", StringUtils.isBlank(ent.getCity()) ? "450100" : ent.getCity());// 所在市编码
                entMap.put("enterpriseAddr", ent.getAddress());// 企业所在地址
                entMap.put("enterpriseSource", "jc_nnwxjc");// 数据来源
                entMap.put("enterpriseRegisterTime", ent.getCreateTime() == null ? null : ent.getCreateTime().getTime());// 企业注册账号的时间
                entMap.put("enterpriseRfidStatus", 1);// 是否开通RFID 0未开通RFID,1开通RFID
                entMap.put("enterpriseJcCoding", ent.getDsidProvince());// 检测企业省网编码，生成上传流水号使用
                entMap.put("enterpriseRegisteredCapital", ent.getFundAmount());// 企业注册资本
                entMap.put("enterpriseBankName", ent.getBankNo());// 开户银行
                entMap.put("enterpriseBankAccountNo", ent.getAcctNo());// 开户银行卡号
                entMap.put("enterpriseOperatingAddress", ent.getAddress());// 企业经营地址
                entMap.put("enterpriseAddrProvince", StringUtils.isBlank(ent.getProvince()) ? "450000" : ent.getProvince());// 企业地址省编码
                entMap.put("enterpriseAddrX", ent.getLongitude());// 企业地址经度
                entMap.put("enterpriseAddrY", ent.getLatitude());// 企业地址纬度
                entMap.put("enterpriseRegisterEmail", ent.getEmail());// 企业邮箱
                entMap.put("enterpriseAddrCity", StringUtils.isBlank(ent.getCity()) ? "450100" : ent.getCity());// 企业地址市编码
                entMap.put("enterpriseAddrDistrict", StringUtils.isBlank(ent.getDistrict()) ? "450101" : ent.getDistrict());// 企业地址区编码
                StringBuilder operationArea = new StringBuilder();
                entFixTypes.forEach(vehicleType -> {
                    VehicleTypeG24 vehicleTypeG24 = vehicleTypeG24Mapper.selectOne(new QueryWrapper<VehicleTypeG24>().eq("vehicle_type_code", vehicleType.getVehicleTypeCode()));
                    if (vehicleTypeG24 != null) {
                        operationArea.append(vehicleTypeG24.getG24Name()).append(",");
                    }
                });
                entMap.put("enterpriseOperationArea", operationArea.length() == 0 ? null : operationArea.deleteCharAt(operationArea.length() - 1).toString());// 企业经营范围
                if (fixFactory != null) {
                    entMap.put("enterpriseBusinessCategory", String.format("%02d", fixFactory.getUnitType()));// 经营类别
                    entMap.put("enterpriseFixAreaIs", fixFactory.getIsAreaLimit());// 是否仅维修本区域车辆(0否1是）
                    entMap.put("enterpriseFixCertificateMonthMax", fixFactory.getCertMonthMax());// 每月合格证数量上限
                    entMap.put("enterpriseFixMonthMax", fixFactory.getFixMonthMax());// 每月最大维修数量上限
                }
                if (inspectSpot != null) {
                    entMap.put("enterpriseJcMetrologicalAuthentication", inspectSpot.getMeasureCertificate());// 计量认证号
                    entMap.put("enterpriseJcMuthenticationUnit", inspectSpot.getIssueUnit());// 认证号发证单位
                    entMap.put("enterpriseJcAuthenticationDateS", inspectSpot.getIssueDate() == null ? null : inspectSpot.getIssueDate().getTime());// 认证号颁布发日期
                    entMap.put("enterpriseJcAuthenticationDateE", inspectSpot.getValidDate() == null ? null : inspectSpot.getValidDate().getTime());// 认证号有效日期
                    entMap.put("enterpriseJcAreaCovered", inspectSpot.getAreaTotal());// 占地总面积
                    entMap.put("enterpriseJcMainCheckArea", inspectSpot.getAreaInspect());// 主检测面积
                    entMap.put("enterpriseJcAuxiliaryArea", inspectSpot.getAreaAssistant());// 辅助面积
                    entMap.put("enterpriseJcParkingArea", inspectSpot.getAreaParking());// 停车场面积
                    entMap.put("enterpriseJcTestLaneSize", inspectSpot.getAreaTrialRun());// 试车道大小
                    entMap.put("enterpriseJcEquipmentTotal", inspectSpot.getEquipmentNum());// 设备总台数
                    entMap.put("enterpriseJcGrade", inspectSpot.getSpotLevel());// 等级(A级站/B级站/C级站)
                    StringBuilder sb = new StringBuilder();
                    sb.append((inspectSpot.getIsFixInspect() != null && inspectSpot.getIsFixInspect() == 1) ? "1" : StringUtils.EMPTY)
                            .append((inspectSpot.getIsGrade() != null && inspectSpot.getIsGrade() == 1) ? ",2" : StringUtils.EMPTY);
                    entMap.put("enterpriseJcScope", sb.indexOf(",") == 0 ? sb.deleteCharAt(0).toString() : sb.toString());// 作业范围（1二维检测2等级评定，多个用,号分割）
                }
                jsonData = JsonUtil.toJson("data", entMap);
                String result = httpClientHelperService.postJSON(dcEntUrl, jsonData, getHeaderToken(), null);
                if (StringUtils.isNotBlank(result)) {
                    String httpstatus = JsonUtil.getValue("httpStatus", result);
                    dcLog.setHttpStatus(httpstatus);
                    String execute = JsonUtil.getValue("execute", result);
                    if (JSONUtil.isJson(execute)) {
                        String status = JsonUtil.getValue("code", execute);
                        if ("200".equals(status)) {
                            String dcId = JsonUtil.getValue("enterpriseId", JsonUtil.getValue("data", execute));
                            Integer entId = ent.getEntId();
                            ent = new Ent();
                            ent.setEntId(entId);
                            ent.setDcId(dcId);
                            entMapper.updateById(ent);
                            dcLog.setStatus(0);
                            dcLog.setStatusCode(status);
                        } else {
                            dcLog.setStatus(1);
                            dcLog.setStatusCode(status);
                        }
                    } else {
                        dcLog.setStatus(1);
                    }
                    dcLog.setUploadResult(JSONUtil.isJson(execute) ? JsonUtil.getValue("msg", execute) : execute);
                } else {
                    dcLog.setStatus(1);
                }
            } catch (Exception e) {
                dcLog.setStatus(1);
                dcLog.setUploadResult(e.getMessage());
                e.printStackTrace();
            }
            dcLog.setRequestParameters(jsonData);
            dcLog.setCreateTime(new Date());
            dcLog.setType(0);
            dcLogMapper.insert(dcLog);
        });
    }

    @Override
    public void uploadTransportationEnt() {
        DcLog uploadTransportationEntLog = dcLogMapper.selectOne(new QueryWrapper<DcLog>().select("max(upload_data_primary_key) uploadDataPrimaryKey").eq("type", 1));
        QueryWrapper<VehicleCompany> queryWrapper = new QueryWrapper<>();
        if (uploadTransportationEntLog != null && uploadTransportationEntLog.getUploadDataPrimaryKey() != null) {
            queryWrapper.gt("vehicle_company_id", uploadTransportationEntLog.getUploadDataPrimaryKey());
        }
        List<VehicleCompany> vehicleCompanyList = vehicleCompanyMapper.selectList(queryWrapper);
        vehicleCompanyList.forEach(vehicleCompany -> {
            String jsonData = null;
            DcLog dcLog = new DcLog();
            dcLog.setUploadDataPrimaryKey(vehicleCompany.getVehicleCompanyId());
            try {
                List<ComScope> comScopes = comScopeMapper.selectList(new QueryWrapper<ComScope>()
                        .eq("vehicle_company_id", vehicleCompany.getVehicleCompanyId()));
                Map<String, Object> vehicleCompanyMap = new HashMap<>();
                long currentDate = System.currentTimeMillis();
                vehicleCompanyMap.put("enterpriseName", vehicleCompany.getCompanyName());//企业全称
                vehicleCompanyMap.put("enterpriseJcShortName", vehicleCompany.getCompanyName());//企业简称
                vehicleCompanyMap.put("enterpriseOwnerPhone", vehicleCompany.getPhone());// 企业负责人电话
                vehicleCompanyMap.put("enterpriseLinkMobile", vehicleCompany.getPhone());// 企业联系电话
                vehicleCompanyMap.put("enterpriseBusinessPhone", vehicleCompany.getPhone());// 企业业务电话
                vehicleCompanyMap.put("enterprisePostalcode", vehicleCompany.getPostcode());// 企业邮政编码
                vehicleCompanyMap.put("enterpriseOperatingState", "01");// 企业经营状态
                vehicleCompanyMap.put("enterpriseIsAudit", 0);// 未备案1已备案 -1被拒绝,2重新申请
                vehicleCompanyMap.put("createTime", currentDate);// 创建时间
                vehicleCompanyMap.put("updateTime", currentDate);// 更新时间
                vehicleCompanyMap.put("enterpriseAddr", vehicleCompany.getAddress());// 企业所在地址
                vehicleCompanyMap.put("enterpriseSource", "ys_nnzchuoyun");// 数据来源
                vehicleCompanyMap.put("enterpriseRegisterTime", currentDate);// 企业注册账号的时间
                vehicleCompanyMap.put("enterpriseOperatingAddress", vehicleCompany.getAddress());// 企业经营地址
                vehicleCompanyMap.put("enterpriseRegisterEmail", vehicleCompany.getEmail());// 企业邮箱
                vehicleCompanyMap.put("enterpriseTypeId", 3);// 企业类型 1维修企业，2检测企业，3运输企业
                vehicleCompanyMap.put("enterpriseDistrict", StringUtils.isBlank(vehicleCompany.getDistrict()) ? "450101" : vehicleCompany.getDistrict());// 企业行政区域
                vehicleCompanyMap.put("enterpriseRegion", StringUtils.isBlank(vehicleCompany.getDistrict()) ? "450101" : vehicleCompany.getDistrict());// 企业所在区域
                vehicleCompanyMap.put("enterpriseBusinessCertificate", vehicleCompany.getLicenceNo());// 企业经营许可证
                vehicleCompanyMap.put("enterpriseEconomicCategory", 3);// 经济性质
                vehicleCompanyMap.put("enterpriseOwner", vehicleCompany.getLegalPerson());// 企业负责人（法人代表）
                vehicleCompanyMap.put("enterpriseAddrProvince", StringUtils.isBlank(vehicleCompany.getProvince()) ? "450000" : vehicleCompany.getProvince());// 企业地址省编码
                vehicleCompanyMap.put("enterpriseAddrCity", StringUtils.isBlank(vehicleCompany.getCity()) ? "450100" : vehicleCompany.getCity());// 企业地址市编码
                vehicleCompanyMap.put("enterpriseAddrDistrict", StringUtils.isBlank(vehicleCompany.getDistrict()) ? "450101" : vehicleCompany.getDistrict());// 企业地址区编码
                vehicleCompanyMap.put("enterpriseRegisteredCapital", vehicleCompany.getFundAmount());// 企业注册资本
                vehicleCompanyMap.put("enterpriseProvince", StringUtils.isBlank(vehicleCompany.getProvince()) ? "450000" : vehicleCompany.getProvince());// 所在省编码
                vehicleCompanyMap.put("enterpriseCity", StringUtils.isBlank(vehicleCompany.getCity()) ? "450100" : vehicleCompany.getCity());// 所在市编码
                vehicleCompanyMap.put("enterpriseLinkName", vehicleCompany.getContactPerson());// 企业联系人
                StringBuilder sb = new StringBuilder();
                comScopes.forEach(comScope -> {
                    ComScopeType comScopeType = comScopeTypeMapper.selectOne(new QueryWrapper<ComScopeType>().eq("scope_type_id", comScope.getScopeTypeId()));
                    if (comScopeType != null) {
                        sb.append(comScopeType.getTypename()).append(",");
                    }
                });
                vehicleCompanyMap.put("enterpriseOperationArea", sb.length() == 0 ? null : sb.deleteCharAt(sb.length() - 1).toString());// 企业经营范围
                jsonData = JsonUtil.toJson("data", vehicleCompanyMap);
                String result = httpClientHelperService.postJSON(dcEntUrl, jsonData, getHeaderToken(), null);
                if (StringUtils.isNotBlank(result)) {
                    String httpStatus = JsonUtil.getValue("httpStatus", result);
                    dcLog.setHttpStatus(httpStatus);
                    String execute = JsonUtil.getValue("execute", result);
                    if (JSONUtil.isJson(execute)) {
                        String status = JsonUtil.getValue("code", execute);
                        if ("200".equals(status)) {
                            String dcId = JsonUtil.getValue("enterpriseId", JsonUtil.getValue("data", execute));
                            Integer vehicleCompanyId = vehicleCompany.getVehicleCompanyId();
                            vehicleCompany = new VehicleCompany();
                            vehicleCompany.setVehicleCompanyId(vehicleCompanyId);
                            vehicleCompany.setDcId(dcId);
                            vehicleCompanyMapper.updateById(vehicleCompany);
                            dcLog.setStatus(0);
                            dcLog.setStatusCode(status);
                        } else {
                            dcLog.setStatus(1);
                            dcLog.setStatusCode(status);
                        }
                    } else {
                        dcLog.setStatus(1);
                    }
                    dcLog.setUploadResult(JSONUtil.isJson(execute) ? JsonUtil.getValue("msg", execute) : execute);
                } else {
                    dcLog.setStatus(1);
                }
            } catch (Exception e) {
                dcLog.setStatus(1);
                dcLog.setUploadResult(e.getMessage());
                e.printStackTrace();
            }
            dcLog.setRequestParameters(jsonData);
            dcLog.setCreateTime(new Date());
            dcLog.setType(1);
            dcLogMapper.insert(dcLog);
        });
    }

    public void uploadInspect() {
        QueryWrapper<Inspect> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("test_id", "690651").le("inspect_date", "2019-08-21 23:59:59");
        int pageNo = 1;
        int total = inspectMapper.selectCount(queryWrapper);
        int totalPage = total % 1000 == 0 ? (total / 1000) : (total / 1000) + 1;
        while (pageNo <= totalPage) {
            IPage<Inspect> iPage = new Page<>(pageNo, 1000);
            IPage<Inspect> page = inspectMapper.selectPage(iPage, queryWrapper);
            List<Inspect> inspects = Optional.ofNullable(page.getRecords()).orElse(new ArrayList<>());
            inspects.forEach(test -> {
                String jsonData = null;
                DcLog dcLog = new DcLog();
                dcLog.setUploadDataPrimaryKey(test.getTestId());
                Map<String, Object> dataMap = new HashMap<>();
                try {
                    Ent ent = entMapper.selectOne(new QueryWrapper<Ent>().eq("ent_id", test.getEntId()));
                    Vehicle vehicle = vehicleMapper.selectOne(new QueryWrapper<Vehicle>().eq("vehicle_id", test.getVehicleId()));
                    List<JcDetectsncreate> jcDetectsncreate = jcDetectsncreateMapper.selectList(new QueryWrapper<JcDetectsncreate>().eq("detectSn", test.getDetectSn()));
                    Map<String, Object> inspectLine = new HashMap<>();
                    List<WorkStation> workStations = workStationMapper.selectList(new QueryWrapper<WorkStation>().eq("work_station_id", test.getWorkStationId()));
                    VehicleTypeG24 vehicleTypeG24 = vehicleTypeG24Mapper.selectOne(new QueryWrapper<VehicleTypeG24>().eq("vehicle_type_code", vehicle == null ? StringUtils.EMPTY : vehicle.getVehicleTypeCode()));
                    if (CollectionUtils.isNotEmpty(workStations)) {
                        inspectLine.put("enterpriseId", ent == null ? null : ent.getDcId());
                        inspectLine.put("inspectLineName", workStations.get(0).getStationNo());
                        inspectLine.put("inspectLineNo", workStations.get(0).getStationNo());
                        inspectLine.put("inspectLineRfid", workStations.get(0).getReaderConfig());
                    }
                    List<EntVideo> entVideos = entVideoMapper.selectList(new QueryWrapper<EntVideo>().eq("ent_id", test.getEntId()));
                    if (CollectionUtils.isNotEmpty(entVideos)) {
                        inspectLine.put("inspectLineVideoIp", entVideos.get(0).getLocalIp());
                        inspectLine.put("inspectLineVideoPort", entVideos.get(0).getOutPort());
                        inspectLine.put("inspectLineVideoChannelNum", entVideos.get(0).getChannelNum());
                        inspectLine.put("inspectLineVideoUser", entVideos.get(0).getUserName());
                        inspectLine.put("inspectLineVideoPass", entVideos.get(0).getPassword());
                        inspectLine.put("inspectLineVideoPluginType", entVideos.get(0).getPlugType());
                    }
                    Map<String, Object> inspect = new HashMap<>();
                    List<EntCostdetail> entCostdetails = entCostdetailMapper.selectList(new QueryWrapper<EntCostdetail>().eq("test_id", test.getTestId()));
                    inspect.put("inspectId", CollectionUtils.isEmpty(jcDetectsncreate) ? "jc_nnwxjc" + test.getTestId() : (StringUtils.isBlank(ent == null ? null : ent.getDsidProvince()) ||
                            StringUtils.isBlank(jcDetectsncreate.get(0).getDetectsnProvince())) ? jcDetectsncreate.get(0).getDetectSn() :
                            jcDetectsncreate.get(0).getDetectsnProvince());
                    if (vehicle != null) {
                        inspect.put("vehicleLicensePlate", vehicle.getLicenceNo());
                        inspect.put("vehicleLicensePlateColor", vehicle.getLicenceColor());
                        inspect.put("vehicleVin", vehicle.getVin());
                        inspect.put("vehicleEngineNo", vehicle.getEngineNo());
                        inspect.put("vehicleTransportCertificateNo", vehicle.getYingyunNo());//vehicle.getEngineNo()
                        inspect.put("vehicleOwnerName", vehicle.getOwnerName());
                        inspect.put("vehicleOwnerTel", vehicle.getOwnerPhone());
                        inspect.put("vehicleOwnerType", (vehicle.getIsPrivate() == null || vehicle.getIsPrivate() == 0) ? "2" : "1");
                    }
                    inspect.put("inspectLineName", CollectionUtils.isEmpty(workStations) ? null : workStations.get(0).getStationNo());
                    if (vehicleTypeG24 != null) {
                        inspect.put("vehicleTypeId", vehicleTypeG24.getG24Code());
                        inspect.put("vehicleTypeName", vehicleTypeG24.getG24Name());
                    }
                    inspect.put("inspectType", "二级维护".equals(test.getInspectMc()) ? "4" : "1");
                    inspect.put("enterpriseId", ent == null ? null : ent.getDcId());
                    inspect.put("provinceDetectsn", CollectionUtils.isEmpty(jcDetectsncreate) ? null : StringUtils.isBlank(jcDetectsncreate.get(0).getCheckcode()) ? null : jcDetectsncreate.get(0).getCheckcode().split(" ")[0]);
                    inspect.put("provinceCheckcode", CollectionUtils.isEmpty(jcDetectsncreate) ? null : StringUtils.isBlank(jcDetectsncreate.get(0).getCheckcode()) ? null : jcDetectsncreate.get(0).getCheckcode().split(" ").length < 2 ? null : jcDetectsncreate.get(0).getCheckcode().split(" ")[1]);
                    inspect.put("inspectCompleted", test.getIsVisa());
                    inspect.put("inspectFile", test.getFileFlag());
                    inspect.put("inspectResult", test.getGrade());
                    inspect.put("inspectDate", test.getModifydate() == null ? new Date().getTime() : test.getModifydate().getTime());
                    inspect.put("inspectCompletedTime", CollectionUtils.isEmpty(entCostdetails) ? test.getModifydate() == null ? new Date().getTime() :
                            test.getModifydate().getTime() : entCostdetails.get(0).getCostTime() == null ? test.getModifydate() == null ? new Date().getTime() :
                            test.getModifydate().getTime() : entCostdetails.get(0).getCostTime().getTime());
                    inspect.put("createTime", test.getModifydate() == null ? new Date().getTime() : test.getModifydate().getTime());
                    Map<String, Object> inspectPic = new HashMap<>();
                    List<InspectImg> inspectImgs = inspectImgMapper.selectList(new QueryWrapper<InspectImg>().eq("inspect_id", test.getTestId()));
                    if (CollectionUtils.isNotEmpty(inspectImgs)) {
                        inspectPic.put("inspectPicStationUrl", inspectImgs.get(0).getImg());
                        inspectPic.put("createTime", inspectImgs.get(0).getCreateTime() == null ? new Date().getTime() : inspectImgs.get(0).getCreateTime().getTime());
                    }
                    List<JcImage> jcImages = jcImageMapper.selectList(new QueryWrapper<JcImage>().eq("inpsectId", test.getTestId()));
                    if (CollectionUtils.isNotEmpty(jcImages)) {
                        jcImages.forEach(image -> {
                            if (image.getType() != null && image.getType() == 3 && "4".equals(image.getImageType())) {
                                inspectPic.put("inspectPicIdcardUrl", image.getImageUrl());
                            }
                            if (image.getType() != null && image.getType() == 1 && "3".equals(image.getImageType())) {
                                inspectPic.put("inspectPicDrivinglicenseUrl", image.getImageUrl());
                            }
                            if (image.getType() != null && image.getType() == 3 && "1".equals(image.getImageType())) {
                                inspectPic.put("inspectPicAllInspectionUrl", image.getImageUrl());
                            }
                            if (image.getType() != null && image.getType() == 1 && "4".equals(image.getImageType())) {
                                inspectPic.put("inspectPicSafePersonUrl", image.getImageUrl());
                            }
                            if (image.getType() != null && image.getType() == 1 && "2".equals(image.getImageType())) {
                                inspectPic.put("inspectPicSafeMarkUrl", image.getImageUrl());
                            }
                            if (image.getType() != null && image.getType() == 1 && "5".equals(image.getImageType())) {
                                inspectPic.put("inspectPicNacktscannerUrl", image.getImageUrl());
                            }
                            if (image.getType() != null && image.getType() == 3 && "2".equals(image.getImageType())) {
                                inspectPic.put("inspectPicManualUrl", image.getImageUrl());
                            }
                            if (image.getType() != null && image.getType() == 3 && "3".equals(image.getImageType())) {
                                inspectPic.put("inspectPicPerformanceUrl", image.getImageUrl());
                            }
                            if (image.getType() != null && image.getType() == 1 && "1".equals(image.getImageType())) {
                                inspectPic.put("inspectPicSafeCheckUrl", image.getImageUrl());
                            }
                            if (image.getType() != null && image.getType() == 4 && "1".equals(image.getImageType())) {
                                inspectPic.put("inspectPicRoadTransportCertUrl", image.getImageUrl());
                            }
                            if (image.getType() != null && image.getType() == 5 && "1".equals(image.getImageType())) {
                                inspectPic.put("inspectPicRoadTransportCertStandardUrl", image.getImageUrl());
                            }
                            if (image.getType() != null && image.getType() == 2 && "1".equals(image.getImageType())) {
                                inspectPic.put("inspectPicEnvironmentalInspectUrl", image.getImageUrl());
                            }
                            if (image.getType() != null && image.getType() == 2 && "2".equals(image.getImageType())) {
                                inspectPic.put("inspectPicEnvironmentalInspect2Url", image.getImageUrl());
                            }
                        });
                    }
                    if (StringUtils.isNotBlank(test.getDetectSn())) {
                        Map<String, Object> params = new HashMap<>();
                        params.put("detectSn", test.getDetectSn());
                        params.put("provinceDetectSn", CollectionUtils.isEmpty(jcDetectsncreate) ? "jc_nnwxjc" + test.getTestId() : (StringUtils.isBlank(ent == null ? null : ent.getDsidProvince()) ||
                                StringUtils.isBlank(jcDetectsncreate.get(0).getDetectsnProvince())) ? jcDetectsncreate.get(0).getDetectSn() :
                                jcDetectsncreate.get(0).getDetectsnProvince());
                        params.put("provinceDsId", (StringUtils.isBlank(ent == null ? null : ent.getDsidProvince()) || StringUtils.isBlank(jcDetectsncreate.get(0).getDsidProvince())) ? ent.getDsId() : jcDetectsncreate.get(0).getDsidProvince());
                        Map<String, Object> jcPerformanceDetectRecord = new HashMap<>();
                        Map<String, Object> jcDetectrecord = dcUploadMapper.getJcPerformanceDetectRecord(params);
                        if (MapUtils.isNotEmpty(jcDetectrecord)) {
                            jcPerformanceDetectRecord.put("jcDetectrecord", dcUploadMapper.getJcPerformanceDetectRecord(params));
                            jcPerformanceDetectRecord.put("jcAppearanceList", dcUploadMapper.getJcAppearanceList(params));
                            jcPerformanceDetectRecord.put("jcBrakewholebrakeseqtimeList", dcUploadMapper.getJcBrakewholebrakeseqtimeList(params));
                            jcPerformanceDetectRecord.put("jcDetectreportList", dcUploadMapper.getJcDetectreportList(params));
                            jcPerformanceDetectRecord.put("jcSingleitemList", dcUploadMapper.jcSingleitemList(params));
                            jcPerformanceDetectRecord.put("jcPowerList", dcUploadMapper.getJcPowerList(params));
                            jcPerformanceDetectRecord.put("jcFueleconomyList", dcUploadMapper.getJcFueleconomyList(params));
                            jcPerformanceDetectRecord.put("jcBrakeinitdataList", dcUploadMapper.getJcBrakeinitdataList(params));
                            jcPerformanceDetectRecord.put("jcBrakewholesinglevehicleList", dcUploadMapper.getJcBrakewholesinglevehicleList(params));
                            jcPerformanceDetectRecord.put("jcBrakesingleaxleList", dcUploadMapper.getJcBrakesingleaxleList(params));
                            jcPerformanceDetectRecord.put("jcBrakewholevehicleserial1List", dcUploadMapper.getJcBrakewholevehicleserial1List(params));
                            jcPerformanceDetectRecord.put("jcBrakewholevehicleserial2List", dcUploadMapper.getJcBrakewholevehicleserial2List(params));
                            jcPerformanceDetectRecord.put("jcBrakewholebrakeseqList", dcUploadMapper.getJcBrakewholebrakeseqList(params));
                            jcPerformanceDetectRecord.put("jcBrakeroadtestparkbrakeList", dcUploadMapper.getJcBrakeroadtestparkbrakeList(params));
                            jcPerformanceDetectRecord.put("jcBrakeroadtestdrivebrakeList", dcUploadMapper.getJcBrakeroadtestdrivebrakeList(params));
                            jcPerformanceDetectRecord.put("jcEmissiongasolinevehicleList", dcUploadMapper.getJcEmissiongasolinevehicleList(params));
                            jcPerformanceDetectRecord.put("jcEmissiondieselvehicleList", dcUploadMapper.getJcEmissiondieselvehicleList(params));
                            jcPerformanceDetectRecord.put("jcSuspensionList", dcUploadMapper.getJcSuspensionList(params));
                            jcPerformanceDetectRecord.put("jcMainLampList", dcUploadMapper.getJcMainlampList(params));
                            jcPerformanceDetectRecord.put("jcImageList", dcUploadMapper.getJcImageList(params));
                            jcPerformanceDetectRecord.put("jcManualtestresultList", dcUploadMapper.getJcManualtestresultList(params));
                            jcPerformanceDetectRecord.put("jcPerformanceItemList", dcUploadMapper.getJcPerformanceItemList(params));
                            jcPerformanceDetectRecord.put("jcSteeringWheelList", dcUploadMapper.getJcSteeringWheelList(params));
                            jcPerformanceDetectRecord.put("jcSoundLevelList", dcUploadMapper.getJcSoundLevelList(params));
                            dataMap.put("jcPerformanceDetectRecord", jcPerformanceDetectRecord);
                        }
                    }
                    if (vehicle != null) {
                        VehicleCompany vehicleCompany = vehicleCompanyMapper.selectOne(new QueryWrapper<VehicleCompany>().eq("vehicle_company_id", vehicle.getVehicleCompanyId()));
                        if ((StringUtils.isNotBlank(vehicle.getOwnerName()) && StringUtils.isNotBlank(vehicle.getOwnerPhone())) ||
                                (vehicleCompany != null && StringUtils.isNotBlank(vehicleCompany.getCompanyName()) && StringUtils.isNotBlank(vehicleCompany.getPhone()))) {
                            Map<String, Object> ownerMap = new HashMap<>();
                            ownerMap.put("ownerType", (vehicle.getIsPrivate() == null || vehicle.getIsPrivate() == 0) ? "2" : "1");
                            ownerMap.put("ownerName", StringUtils.isBlank(vehicle.getOwnerName()) ? vehicleCompany == null ? null : vehicleCompany.getCompanyName() : vehicle.getOwnerName());
                            ownerMap.put("ownerMobile", StringUtils.isBlank(vehicle.getOwnerPhone()) ? vehicleCompany == null ? null : vehicleCompany.getPhone() : vehicle.getOwnerPhone());
                            ownerMap.put("ownerTel", StringUtils.isBlank(vehicle.getOwnerPhone()) ? vehicleCompany == null ? null : vehicleCompany.getPhone() : vehicle.getOwnerPhone());
                            ownerMap.put("ownerFax", StringUtils.isBlank(vehicle.getOwnerFax()) ? vehicleCompany == null ? null : vehicleCompany.getFax() : vehicle.getOwnerFax());
                            ownerMap.put("ownerEmail", StringUtils.isBlank(vehicle.getOwnerEmail()) ? vehicleCompany == null ? null : vehicleCompany.getEmail() : vehicle.getOwnerEmail());
                            ownerMap.put("ownerPostcode", StringUtils.isBlank(vehicle.getOwnerPostcode()) ? vehicleCompany == null ? null : vehicleCompany.getPostcode() : vehicle.getOwnerPostcode());
                            ownerMap.put("createTime", vehicle.getCreateDate() == null ? new Date().getTime() : vehicle.getCreateDate().getTime());
                            ownerMap.put("ownerAddress", StringUtils.isBlank(vehicle.getOwnerAddress()) ? vehicleCompany == null ? null : vehicleCompany.getAddress() : vehicle.getOwnerAddress());
                            Map<String, Object> data = new HashMap<>();
                            data.put("data", ownerMap);
                            dataMap.put("ownerRequest", data);
                        }
                        long currentDate = System.currentTimeMillis();
                        long createDate = vehicle.getCreateDate() == null ? new Date().getTime() : vehicle.getCreateDate().getTime();
                        Map<String, Object> requestMap = new HashMap<>();
                        Map<String, Object> vehicleMap = new HashMap<>();
                        vehicleMap.put("vehicleLicensePlate", vehicle.getLicenceNo());//车牌号码
                        vehicleMap.put("vehicleLicensePlateColor", vehicle.getLicenceColor());//车牌颜色
                        vehicleMap.put("vehicleVin", vehicle.getVin());//车架号（vin码）
                        vehicleMap.put("vehicleTypeId", "99");//车辆类型 4
                        vehicleMap.put("vehicleUse", "01");//使用性质（01营运02非营运）
                        vehicleMap.put("vehicleOwnerId", "01");//车主ID
                        vehicleMap.put("vehicleOwnerName", StringUtils.isBlank(vehicle.getOwnerName()) ? vehicleCompany == null ? null : vehicleCompany.getCompanyName() : vehicle.getOwnerName());//车主名称（业户名称/车主单位）
                        vehicleMap.put("vehicleOwnerTelphone", StringUtils.isBlank(vehicle.getOwnerPhone()) ? vehicleCompany == null ? null : vehicleCompany.getPhone() : vehicle.getOwnerPhone());//车主联系电话
                        vehicleMap.put("vehicleOwnerType", (vehicle.getIsPrivate() == null || vehicle.getIsPrivate() == 0) ? "2" : "1");//车主类型(个人/企业（1个人,2企业））
                        vehicleMap.put("vehicleFuelCategoryId", VehicleEnums.OilType.getValue(vehicle.getOilType()));//燃油类别
                        vehicleMap.put("vehicleEngineNumber", vehicle.getEngineNo());//发动机号码
                        vehicleMap.put("createTime", createDate);//创建时间
                        vehicleMap.put("updateTime", currentDate);//更新时间
                        vehicleMap.put("deleteStatus", 0);//删除状态
                        vehicleMap.put("province", StringUtils.isBlank(vehicle.getPopedom()) ? "450000" : vehicle.getPopedom().substring(0, 2) + "0000");//省编码
                        vehicleMap.put("city", StringUtils.isBlank(vehicle.getPopedom()) ? "450100" : vehicle.getPopedom().substring(0, 4) + "00");//市编码
                        vehicleMap.put("region", StringUtils.isBlank(vehicle.getPopedom()) ? "450101" : vehicle.getPopedom());//区编码
                        vehicleMap.put("vehicleSourceCode", "jc_nnwxjc");//数据来源
                        vehicleMap.put("vehicleBodyColor", VehicleEnums.AutoColor.getValue(vehicle.getAutoColor()));//车身颜色国标代码
                        if (vehicleCompany != null && StringUtils.isNotBlank(vehicleCompany.getDcId())) {
                            vehicleMap.put("enterpriseId", vehicleCompany.getDcId());//车辆所属单位
                        }
                        requestMap.put("vehicle", vehicleMap);
                        Map<String, Object> vehicleTechnologyMap = new HashMap<>();
                        //车辆技术参数
                        vehicleTechnologyMap.put("vehicleTechnologyTypeId", vehicleTypeG24 == null ? null : vehicleTypeG24.getG24Code());//运输车辆类型ID(200多项)
                        vehicleTechnologyMap.put("vehicleTechnologyManufactureName", vehicle.getCarSupplier());//制造厂名(制造单位)
                        vehicleTechnologyMap.put("vehicleTechnologyProductDate", vehicle.getOutDate());//出厂日期
                        vehicleTechnologyMap.put("vehicleTechnologyDomesticImport", "01");//国产/进口
                        vehicleTechnologyMap.put("vehicleTechnologyVin", vehicle.getVin());//VIN（或车架号）
                        vehicleTechnologyMap.put("vehicleTechnologyChassisModel", vehicle.getUnderPanNo());//底盘型号
                        vehicleTechnologyMap.put("vehicleTechnologyDimensions", vehicle.getOutLineSize());//车辆外廓尺寸（长:宽:高）
                        vehicleTechnologyMap.put("vehicleTechnologyTotalMass", vehicle.getFullWeight());//总质量
                        vehicleTechnologyMap.put("vehicleTechnologyUnladenMass", vehicle.getEmptyWeight());//整备质量
                        vehicleTechnologyMap.put("vehicleTechnologyApprovedCarry", vehicle.getSeat());//核定载客
                        vehicleTechnologyMap.put("vehicleTechnologyEngineType", vehicle.getEngineType());//发动机型号
                        vehicleTechnologyMap.put("vehicleTechnologyEngineNo", vehicle.getEngineNo());//发动机号码
                        vehicleTechnologyMap.put("vehicleTechnologyEnginePower", vehicle.getRatedPower());//发动机净功率
                        vehicleTechnologyMap.put("vehicleTechnologyPowerType", vehicle.getDriveType());//动力类型
                        vehicleTechnologyMap.put("vehicleTechnologyAxleNum", vehicle.getAxesNum());//车轴数量
                        vehicleTechnologyMap.put("vehicleTechnologyWheelbase", vehicle.getAxesDistance());//轴距
                        vehicleTechnologyMap.put("vehicleTechnologyTiresSpecifications", vehicle.getTyreSize());//轮胎数/规格
                        vehicleTechnologyMap.put("vehicleTechnologyVehiclepic", vehicle.getLicenceImg());//车辆照片
                        vehicleTechnologyMap.put("createTime", createDate);//常见时间
                        vehicleTechnologyMap.put("updateTime", currentDate);//更新时间
                        vehicleTechnologyMap.put("deleteStatus", 0);//删除状态
                        requestMap.put("vehicleTechnology", vehicleTechnologyMap);
                        List<Map<String, Object>> vehicleLicensePlateList = new ArrayList<>();
                        Map<String, Object> vehicleLicensePlateMap = new HashMap<>();
                        //车辆变更记录
                        vehicleLicensePlateMap.put("vehicleLicensePlateId", null);//车辆号牌变更ID
                        vehicleLicensePlateMap.put("vehicleLicensePlate", vehicle.getLicenceNo());//车牌号码
                        vehicleLicensePlateMap.put("vehicleLicensePlateColor", vehicle.getLicenceColor());//车牌颜色
                        vehicleLicensePlateMap.put("vehicleLicensePlateDate", createDate);//注册变更日期
                        vehicleLicensePlateMap.put("createTime", createDate);//创建时间
                        vehicleLicensePlateMap.put("updateTime", currentDate);//更新时间
                        vehicleLicensePlateMap.put("deleteStatus", 0);//删除状态
                        vehicleLicensePlateList.add(vehicleLicensePlateMap);
                        requestMap.put("vehicleLicensePlateList", vehicleLicensePlateList);
                        if (StringUtils.isNotBlank(vehicle.getYingyunNo())) {
                            List<Map<String, Object>> transportCertificateList = new ArrayList<>();
                            Map<String, Object> transportCertificateMap = new HashMap<>();
                            //道路运输证记录
                            transportCertificateMap.put("vehicleTransportCertificateBusinessname", StringUtils.isBlank(vehicle.getOwnerName()) ? vehicleCompany == null ? null : vehicleCompany.getCompanyName() : vehicle.getOwnerName());//业户名称
                            transportCertificateMap.put("vehicleTransportCertificateNo", vehicle.getYingyunNo());//道路运输证号
                            transportCertificateMap.put("vehicleTransportCertificateChangeDate", createDate);//变更日期
                            transportCertificateMap.put("createTime", createDate);//创建时间
                            transportCertificateMap.put("updateTime", currentDate);//更新时间
                            transportCertificateMap.put("deleteStatus", 0);//删除状态
                            transportCertificateList.add(transportCertificateMap);
                            requestMap.put("vehicleTransportCertificates", transportCertificateList);
                        }
                        dataMap.put("vehicleInfoRequest", requestMap);
                    }
                    dataMap.put("enterpriseId", ent == null ? null : ent.getDcId());
                    dataMap.put("inspect", inspect);
                    dataMap.put("inspectLine", inspectLine);
                    dataMap.put("inspectPic", inspectPic);
                    jsonData = JsonUtil.serialize(dataMap);
                    String result = httpClientHelperService.postJSON(dcInspectUrl, jsonData, getHeaderToken(), null);
                    if (StringUtils.isNotBlank(result)) {
                        String httpStatus = JsonUtil.getValue("httpStatus", result);
                        dcLog.setHttpStatus(httpStatus);
                        String execute = JsonUtil.getValue("execute", result);
                        System.out.println(execute);
                        if ("true".equals(execute)) {
                            dcLog.setStatus(0);
                        } else {
                            dcLog.setStatus(1);
                        }
                        dcLog.setUploadResult(execute);
                    } else {
                        dcLog.setStatus(1);
                    }
                } catch (Exception e) {
                    dcLog.setStatus(1);
                    dcLog.setUploadResult(e.getMessage());
                    e.printStackTrace();
                }
                dcLog.setRequestParameters(jsonData);
                dcLog.setCreateTime(new Date());
                dcLog.setType(3);
                dcLogMapper.insert(dcLog);
            });
            pageNo++;
        }
    }

    private Map<String, Object> getHeaderToken() {
        String token = this.loginDc();
        Map<String, Object> header = new HashMap<>();
        header.put("token", token);
        return header;
    }

    private String loginDc() {
        Map<String, Object> request = new HashMap<>();
        request.put("username", username);
        request.put("password", password);
        String jsonData = JsonUtil.serialize(request);
        String result = httpClientHelperService.postJSON(dc_login_interface_url, jsonData, null, null);
        System.out.println("请求结果===========================>" + result);
        if (StringUtils.isNotBlank(result)) {
            ResponseDto response = JsonUtil.jsonToObject(JsonUtil.getValue("execute", result), ResponseDto.class);
            if (response != null && response.getCode() != null && response.getCode() == 200) {
                return response.getToken();
            }
        }
        return null;
    }

}
