package com.wjk.sprlay.exception;

import com.wjk.sprlay.web.vo.ResultData;

/**
 * 
 * @ClassName:  SprlayException   
 * @Description:自定义异常   
 * @author: WangJKui
 * @date:   2019年1月24日 上午9:12:03   
 *
 */
public class SprlayException extends RuntimeException{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = -2014836825257070123L;
	
	private Exception exception;
	private ResultData resultData;
	
	public SprlayException(ResultData resultData, Exception exception){
		this.resultData = resultData;
		this.exception = exception;
	}
	
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	public ResultData getResultData() {
		return resultData;
	}
	public void setResultData(ResultData resultData) {
		this.resultData = resultData;
	}




}
