package com.ycxc.upload.mapper.bus;

import java.util.List;
import java.util.Map;

public interface DcUploadMapper {

	/**
	 * 外检项目检测
	 * @param params 条件参数
	 * @return 外检项目检测
	 */
	List<Map<String,Object>> getJcAppearanceList(Map<String, Object> params);

	/**
	 * 检验报告单
	 * @param params 条件参数
	 * @return 检验报告单
	 */
	List<Map<String,Object>> getJcDetectreportList(Map<String, Object> params);

	/**
	 * 单项检测
	 * @param params 条件参数
	 * @return 单项检测
	 */
	List<Map<String,Object>> jcSingleitemList(Map<String, Object> params);
	
	/**
	 * 获取检验报告人工检验结果记录列表
	 * @param params 条件参数
	 * @return 检验报告人工检验结果记录列表
	 */
	List<Map<String,Object>> getJcManualtestresultList(Map<String, Object> params);
	
	/**
	 * 获取检验报告性能检验结果记录列表
	 * @param params 条件参数
	 * @return 检验报告性能检验结果记录列表
	 */
	List<Map<String,Object>> getJcPerformanceItemList(Map<String, Object> params);
	
	/**
	 * 获取性能检测数据基础信息
	 * @param params 条件参数
	 * @return 性能检测数据基础信息
	 */
	Map<String,Object> getJcPerformanceDetectRecord(Map<String, Object> params);
	
	/**
	 * 获取侧滑量数据
	 * @param params 条件参数
	 * @return 侧滑量数据
	 */
	List<Map<String,Object>> getJcSteeringWheelList(Map<String, Object> params);
	
	/**
	 * 获取喇叭数据
	 * @param params 条件参数
	 * @return 喇叭数据
	 */
	List<Map<String,Object>> getJcSoundLevelList(Map<String, Object> params);
	
	/**
	 * 获取动力记录数据
	 * @param params 条件参数
	 * @return 动力记录数据
	 */
    List<Map<String,Object>> getJcPowerList(Map<String, Object> params);
    
    /**
     * 获取燃料经济性记录数据
     * @param params 条件参数
     * @return 燃料经济性记录数据
     */
	List<Map<String,Object>> getJcFueleconomyList(Map<String, Object> params);
	
	/**
	 * 获取制动性原始数据单轴记录数据
	 * @param params 条件参数
	 * @return 制动性原始数据单轴记录数据
	 */
	List<Map<String,Object>> getJcBrakeinitdataList(Map<String, Object> params);
	
	/**
	 * 获取制动性整车单车记录数据 
	 * @param params 条件参数
	 * @return 制动性整车单车记录数据 
	 */
	List<Map<String,Object>> getJcBrakewholesinglevehicleList(Map<String, Object> params);
	
	/**
	 * 获取制动性原始数据判定记录数据 
	 * @param params 条件参数
	 * @return 制动性原始数据判定记录数据
	 */
	List<Map<String,Object>> getJcBrakesingleaxleList(Map<String, Object> params);
	
	/**
	 * 获取制动性挂车、牵引车整车制动率记录数据
	 * @param params 条件参数
	 * @return 制动性挂车、牵引车整车制动率记录数据
	 */
	List<Map<String,Object>> getJcBrakewholevehicleserial1List(Map<String, Object> params);
	
	/**
	 * 获取制动性挂车、牵引车整车制动率比记录数据 
	 * @param params 条件参数
	 * @return 制动性挂车、牵引车整车制动率比记录数据
	 */
	List<Map<String,Object>> getJcBrakewholevehicleserial2List(Map<String, Object> params);
	
	/**
	 * 获取制动时序数据
	 * @param params 条件参数
	 * @return 制动时序数据
	 */
	List<Map<String,Object>> getJcBrakewholebrakeseqList(Map<String, Object> params);
	
	/**
	 * 获取制动时序时间数据
	 * @param params 条件参数
	 * @return 制动时序时间数据
	 */
	List<Map<String,Object>> getJcBrakewholebrakeseqtimeList(Map<String, Object> params);
	
	/**
	 * 获取路试驻车制动记录信息 数据
	 * @param params 条件参数
	 * @return 路试驻车制动记录信息 数据
	 */
	List<Map<String,Object>> getJcBrakeroadtestparkbrakeList(Map<String, Object> params);
	
	/**
	 * 获取路试行车制动记录信息数据
	 * @param params 条件参数
	 * @return 路试行车制动记录信息数据
	 */
	List<Map<String,Object>> getJcBrakeroadtestdrivebrakeList(Map<String, Object> params);
	
	/**
	 * 获取排放性汽油车记录数据
	 * @param params 条件参数
	 * @return 排放性汽油车记录数据
	 */
	List<Map<String,Object>> getJcEmissiongasolinevehicleList(Map<String, Object> params);
	
	/**
	 * 获取排放性柴油车记录数据
	 * @param params 条件参数
	 * @return 排放性柴油车记录数据
	 */
	List<Map<String,Object>> getJcEmissiondieselvehicleList(Map<String, Object> params);
	
	/**
	 * 获取悬架记录数据
	 * @param params 条件参数
	 * @return 悬架记录数据
	 */
	List<Map<String,Object>> getJcSuspensionList(Map<String, Object> params);
	
	/**
	 * 获取前照灯数据
	 * @param params 条件参数
	 * @return 前照灯数据
	 */
	List<Map<String,Object>> getJcMainlampList(Map<String, Object> params);
	
	/**
	 * 获取检验工位照片信息
	 * @param params 条件参数
	 * @return 检验工位照片信息
	 */
	List<Map<String,Object>> getJcImageList(Map<String, Object> params);
	
}
