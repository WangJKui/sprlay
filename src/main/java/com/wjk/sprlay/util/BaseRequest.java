package com.wjk.sprlay.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 基础请求数据对象，用于接收视图层的请求参数。
 * <p>
 * 通常在处理器中申明一个该类型的参数，可接收名为"json"的JSON格式的参数，并自动解析生成一个
 * <code>Map&lt;String, Object&gt;</code>类型的Map属性，可通过{@link #getParams()}
 * 获取该Map属性。
 * <p>
 * 视图层的所有请求参数通常存储在一个Plain-Object对象中，最终转换成一个JSON格式的字符串后，以"json"参数提交。
 * 
 * @author waf
 */
public class BaseRequest {
	/**
	 * JSON格式的请求字符串。
	 */
	private String json = null;

	/**
	 * 由属性"json"解析后生成的Map类型的请求参数
	 */
	private Map<String, Object> params = null;

	/**
	 * 获取json格式的请求字符串。
	 * 
	 * @return json格式的请求字符串
	 */
	public String getJson() {
		return json;
	}

	/**
	 * 设置json格式的请求字符串并自动解析生成一个<code>Map&lt;String, Object&gt;</code>类型的属性。
	 * <p>
	 * 该函数通常由Spring框架自动调用。
	 * 
	 * @param json
	 *            json格式的请求字符串
	 */
	public void setJson(String json) {
		this.json = json;
		params = JsonUtils.parseJSON2Map(json);
	}

	/**
	 * 获取由json参数解析后生成的<code>Map&lt;String, Object&gt;</code>类型的属性。
	 * 
	 * @return 由json参数解析后生成的属性
	 */
	public Map<String, Object> getParams() {
		if (params == null) {
			return new HashMap<String, Object>();
		} else {
			return params;
		}
	}

	/**
	 * 按参数返回json参数解析后生成的<code>Map&lt;String, Object&gt;</code>中的对象值。
	 * 
	 * @param paramName 字符串型参数名
	 * @return 指定的对象值。
	 *         null：无对应值
	 */
	@SuppressWarnings("unchecked")
	public <T> T getParam(String paramName) {
		
		T result = null;
		
		if (params != null) {
			result = (T) params.get(paramName);
		}
		
		return result;
	}

	/**
	 * 设置请求参数
	 * 
	 * @param params
	 *            解析后的请求参数
	 */
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	/**
	 * 添加参数
	 * @param key
	 * @param obj
	 */
	public void putToParams(String key, Object obj) {
		this.params.put(key, obj);
	}
	
	/**
	 * 把json格式请求中名为param的参数解析成指定VO类型的数组。
	 * 
	 * @param clz
	 *            VO类型
	 * @param param
	 *            参数名
	 * @return 指定VO类型的数组
	 * @throws Exception
	 *             异常
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> parseForVOs(Class<T> clz, String param) {
		Object rows = getParams().get(param);
		if (rows == null) {
			return null;
		}

		List<T> vos = new ArrayList<T>();

		List<Map<String, Object>> list = (ArrayList<Map<String, Object>>) rows;

		for (Map<String, Object> row : list) {
			vos.add(SprUtil.parseToPOJO(clz, row));
		}

		return vos;
	}

	/**
	 * 把json格式请求中名为param的参数解析成指定类型的VO对象。
	 * 
	 * @param clz
	 *            VO类型
	 * @param param
	 *            参数名
	 * @return VO对象
	 * @throws Exception
	 *             异常
	 */
	@SuppressWarnings("unchecked")
	public <T> T parseForVO(Class<T> clz, String param) {
		Object obj = getParams().get(param);
		if (obj == null) {
			return null;
		}

		Map<String, Object> mapParseFrom = (Map<String, Object>) obj;

		return SprUtil.parseToPOJO(clz, mapParseFrom);
	}
}
