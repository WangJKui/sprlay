package com.wjk.sprlay.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * JSON格式字符串处理
 * 
 * @author waf
 */
public abstract class JsonUtils {

	/**
	 * 把JSON格式字符串转换成一个列表。
	 * <p>
	 * 每个列表项为一个Map对象，字符串格式如：[{key1:..., key2:...},{...},...]
	 * 
	 * @param json
	 *            JSON格式字符串
	 * @return 列表
	 */
	public static List<Map<String, Object>> parseJSON2List(String json) {
		JSONArray jsonArr = JSONArray.parseArray(json);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Iterator<Object> it = jsonArr.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			list.add(parseJSON2Map(obj.toString()));
		}

		return list;
	}

	/**
	 * 把JSON格式字符串转换成一个Map映射对象。
	 * <p>
	 * JSON格式字符串格式如：{key1:..., key2:...}
	 * 
	 * @param json
	 *            JSON格式字符串
	 * @return Map映射对象
	 */
	public static Map<String, Object> parseJSON2Map(String json) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (SprUtil.isEmpty(json)) {
			return map;
		}

		JSONObject jsonObj = JSONObject.parseObject(json);
		for (Object key : jsonObj.keySet()) {
			Object val = jsonObj.get(key);

			if (val instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<Object> it = ((JSONArray) val).iterator();
				while (it.hasNext()) {
					Object obj = it.next();
					list.add(parseJSON2Map(obj.toString()));
				}
				map.put(key.toString(), list);
			} else {
				map.put(key.toString(), val);
			}
		}

		return map;
	}
}
