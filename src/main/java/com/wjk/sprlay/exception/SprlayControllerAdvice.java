//package com.wjk.sprlay.exception;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.wjk.sprlay.web.vo.ResultData;
//
///**
// * 
// * @ClassName:  SprlayControllerAdvice   
// * @Description:全局捕获异常和自定义全局捕获异常  
// * @author: WangJKui
// * @date:   2019年1月24日 上午8:57:19   
// *
// */
//@ControllerAdvice
//public class SprlayControllerAdvice {
//
//	private static Logger logger = LoggerFactory.getLogger(SprlayControllerAdvice.class); 
//
//	/**
//	 * 
//	 * @Title: exceptionHandler   
//	 * @Description: 全局异常处理   
//	 * @param: @param ex
//	 * @param: @return      
//	 * @return: ResultData      
//	 * @throws
//	 */
//	@ResponseBody
//	@ExceptionHandler(value = Exception.class)
//	public ResultData exceptionHandler(Exception ex){
//        logger.error("捕获到Exception异常",ex);
//
//		return new ResultData(-1, "未知错误，请联系系统管理员！"+ex.getLocalizedMessage());
//	}
//
//	/**
//	 * 
//	 * @Title: sprErrorHandler   
//	 * @Description: 拦截捕捉自定义异常    
//	 * @param: @param ex
//	 * @param: @return      
//	 * @return: ResultData      
//	 * @throws
//	 */
//	@ResponseBody
//	@ExceptionHandler(value = SprlayException.class)
//	public ResultData sprErrorHandler(SprlayException ex) {
//        logger.error("捕获到SprlayException异常",ex);
//
//		return ex.getResultData() ;
//	}
//
//}
