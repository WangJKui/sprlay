package com.wjk.sprlay.web.vo;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static Logger log = LoggerFactory.getLogger(ResultData.class); 

	private Integer code = 0;
	private String msg = "成功";
	private Object data;
	
	
	/**
	 * 成功
	 * @Title ok   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @param @return      
	 * @return ResultData
	 */
	public static ResultData ok() {
		
		ResultData ok = new ResultData();
		
		if (log.isInfoEnabled()) {
			log.info("response data was wrote: \r\n{}", ok.toString());
		}
		
		return ok;
	}
	
	
	/**
	 * 成功
	 * @Title ok   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @param @param data
	 * @param @return      
	 * @return ResultData
	 */
	public static ResultData ok(Object data) {
		
		ResultData ok = new ResultData(data);
		
		if (log.isInfoEnabled()) {
			log.info("response data was wrote: \r\n{}", ok.toString());
		}
		
		return ok;
	}
	
	/**
	 * 失败
	 * @Title error   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @param @param code
	 * @param @param msg
	 * @param @return      
	 * @return ResultData
	 */
	public static ResultData error(Integer code, String msg) {
	
		ResultData error = new ResultData(code,msg);
		
		if (log.isInfoEnabled()) {
			log.info("response data was wrote: \r\n{}", error.toString());
		}
		return error;
	}
	
	private ResultData() {
	}
	
	private ResultData(Object data) {
		super();
		this.data = data;
	}
	
	private ResultData(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	private ResultData(Integer code, String msg, Object data) {
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
