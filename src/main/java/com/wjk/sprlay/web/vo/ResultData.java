package com.wjk.sprlay.web.vo;

import java.io.Serializable;

/**
 * 
 * @ClassName:  resultData   
 * @Description:返回结果
 * @author: WangJKui
 * @date:   2019年1月22日 下午4:23:41   
 *
 */
public class ResultData implements Serializable{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = 4562369066030846486L;
	
	private Integer code = 0;
	private String msg = "成功";
	private Object data;
	
	public ResultData() {
	}
	
	public ResultData(Object data) {
		super();
		this.data = data;
	}
	
	public ResultData(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public ResultData(Integer code, String msg, Object data) {
		this(code,msg);
		this.data = data;
	}
	
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "resultData [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
	

}
