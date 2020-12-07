package com.ycxc.upload.common.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * Json工具类
 * @author ly
 *
 */

public class JsonUtil {
	
	/**
	 * json转List
	 * @param str
	 * @param cls
	 * @return
	 */
	public static final <T> List<T> getList(String str,Class<T> cls){
		List<T> list = JSON.parseArray(str,cls);
		return list;
	}
	
	/**
	 * 将Json文本数据信息转换为JsonObject对象,获取Value
	 * @param key    "name"
	 * @param json    {"name":"中车信息"}
	 * @return "中车信息"
	 */
	public static String getValue(String key,String json){
		JSONObject object = JSON.parseObject(json);
		if(object==null){
			return null;
		}
		Object obj=object.get(key);
		if(obj==null){
			return null;
		}
		return obj.toString();
	}
	
	/**
	 * 通过json格式，返回JavaBean对象
	 * @param <T>
	 * @param json eg:{"name":"中车信息"}
	 * @param cls  User.class
	 * @return 
	 */
	public static <T> T jsonToObject(String json,Class<T> cls){
		return JSON.parseObject(json, cls);
	}
	
	//以下方法是将对象转成Json格式字符串
	/**
	 * 通过对象转换成json字符串,返回有指定key值的json
	 * @param <T>
	 * @return eg:{"name":"中车信息"}
	 */
	public static <T> String toJson(String key,T o){
		JSONObject json = new JSONObject();
		json.put(key,o);
		return json.toString();
	}
	
	/**
	 * 传入对象直接返回Json
	 * @param object
	 * @return
	 */
	 public static <T> String serialize(T object){
		 return JSON.toJSONString(object,SerializerFeature.DisableCircularReferenceDetect);
     }
	 
	 /**
	  * 将对象转换成Json格式字符串，并过滤多余的字段。
	  *
	  * @param object 
	  * @param filter 过滤器
	  * @return
	  */
	 public static <T> String serialize(T object, PropertyFilter filter){
		return JSON.toJSONString(object,filter,SerializerFeature.DisableCircularReferenceDetect);
	 }
	
	 /**
	  * json转bean或list
	  * @param str 带转换字符串
	  * @param typeReference 转换类型
	  * @return T
	  */
	 public static final <T> T getObject(String str,TypeReference<T> typeReference){
		 return JSON.parseObject(str,typeReference);
	 }

}
