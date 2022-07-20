package com.java.util;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
	/*
	 * 把对象转换为JSON的工具方�?
	 */
	public static String ObjectRoJsonString(Object obj){
		return JSON.toJSONString(obj, true);
	}
}
