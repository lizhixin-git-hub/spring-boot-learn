package com.ycxc.upload.common.response;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Administrator
 */
public class R<T> extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("status", 200);
		put("message", "success");
	}
	
	public static R error() {
		return error(201, "数据检验错误。");
	}
	
	public static R error(String message) {
		return error(201, message);
	}
	
	public static R error(int status, String message) {
		R r = new R();
		r.put("status", status);
		r.put("message", message);
		return r;
	}

	public static R ok(String message) {
		R r = new R();
		r.put("message", message);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
