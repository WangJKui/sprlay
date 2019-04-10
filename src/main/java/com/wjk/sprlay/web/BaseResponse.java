package com.wjk.sprlay.web;


import java.util.HashMap;
import java.util.Map;

/**
 * 基础响应数据对象，用于向视图层返回请求处理信息。
 * <p>
 * 对视图层请求的处理后的响应信息通常存储在一个对象中，最终转换成一个JSON格式的字符串后输出。
 * @ClassName  BaseResponse
 * @Description 
 * @author WangJKui
 * @date   2019年4月10日 上午8:52:12
 */
public class BaseResponse extends BaseJson {
	/**
	 * 本响应对象对应的请求类型。
	 */
	private String requestType = null;

	/**
	 * 通过响应对象返回的属性信息。
	 */
	private Map<String, Object> attrs = null;

	/**
	 * 缺省构造函数。
	 */
	public BaseResponse() {
	}


	/**
	 * 获取请求类型。
	 * 
	 * @return 请求类型
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * 设置请求类型。
	 * <p>
	 * 请求类型用于标识不同的请求，可为任意字符串。<br>
	 * 如列表保存请求类型为"list.save"，列表删除类型为"list.delete"等
	 * 
	 * @param requestType
	 *            请求类型
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}


	/**
	 * 获取Map属性对象。
	 * 
	 * @return Map属性对象
	 */
	public Map<String, Object> getAttrs() {
		return attrs;
	}

	/**
	 * 从Map属性对象中获取指定属性的值
	 * 
	 * @param attr
	 *            属性
	 * @return 指定属性的值。不存在指定属性时返回null
	 */
	public Object getFromAttrs(String attr) {
		if (attrs == null) {
			return null;
		}

		return attrs.get(attr);
	}

	/**
	 * 设置Map属性对象。
	 * 
	 * @param attrs
	 *            Map属性对象
	 */
	public void setAttrs(Map<String, Object> attrs) {
		this.attrs = attrs;
	}

	/**
	 * 在Map属性对象中添加属性attr，值为value。
	 * 
	 * @param attr
	 *            属性
	 * @param value
	 *            属性值
	 */
	public void putToAttrs(String attr, Object value) {
		if (attrs == null) {
			attrs = new HashMap<String, Object>();
		}

		attrs.put(attr, value);
	}

	/**
	 * 从Map属性对象中删除指定属性。
	 * 
	 * @param attr
	 *            需要删除属性
	 * @return 被删除的属性值。不存在指定属性时返回null
	 */
	public Object removeFromAttrs(String attr) {
		if (attrs == null) {
			return null;
		}

		return attrs.remove(attr);
	}

	/**
	 * 清空所有属性
	 */
	public void clearAttrs() {
		if (attrs != null) {
			attrs.clear();
		}
	}
}