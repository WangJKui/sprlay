package com.wjk.sprlay.web;

/**
 * 
 * @ClassName  BaseJson
 * @Description 基础的json格式响应对象，可用于任意操作或处理后的响应对象。
 * @author WangJKui
 * @date   2019年4月10日 上午8:50:50
 */
public class BaseJson {
	private boolean success = true;
	private String message = null;
	private String messageCode = null;
	private String detailMessage = null;
	private Object data = null;

	/**
	 * 判断处理是否成功。
	 * 
	 * @return 是否成功
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 设置处理是否成功。
	 * 
	 * @param success
	 *            是否成功
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * 获取响应信息。
	 * <p>
	 * 该响应信息一般表示处理的简短说明
	 * 
	 * @return 响应信息
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置响应信息。
	 * 
	 * @param message
	 *            响应信息
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 获取详细响应信息。
	 * <p>
	 * 该详细响应信息一般表示处理的详细说明
	 * 
	 * @return 详细响应信息
	 */
	public String getDetailMessage() {
		return detailMessage;
	}

	/**
	 * 设置详细响应信息。
	 * 
	 * @param detailMessage
	 *            详细响应信息
	 */
	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

	/**
	 * 获取消息码
	 * 
	 * @return 消息码
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * 设置消息码
	 * 
	 * @param messageCode
	 *            消息码
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	/**
	 * 获取响应数据
	 * 
	 * @return 响应数据
	 */
	@SuppressWarnings("unchecked")
	public <T> T getData() {
		return (T) data;
	}

	/**
	 * 设置响应数据
	 * 
	 * @param data
	 *            响应数据
	 */
	public void setData(Object data) {
		this.data = data;
	}
}
