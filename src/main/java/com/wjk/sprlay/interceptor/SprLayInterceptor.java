package com.wjk.sprlay.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName:  SprLayInterceptor   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年1月18日 上午9:08:30   
 *
 */
@Component
public class SprLayInterceptor implements HandlerInterceptor{
	
    private static Logger logger = LoggerFactory.getLogger(SprLayInterceptor.class); 

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		logger.debug("getRequestURL："+request.getRequestURL());
		logger.debug("getServerName："+request.getServerName());
		logger.debug("getServerPort："+request.getServerPort());
		logger.debug("getContextPath："+request.getContextPath());
		logger.debug("getServletPath："+request.getServletPath());
		//page=1&limit=10 get请求显示
		logger.debug("getQueryString："+request.getQueryString());

		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {

		Long start = (Long) request.getAttribute("startTime");
		logger.debug("耗时:"+(System.currentTimeMillis() - start));
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
			throws Exception {

		Long start = (Long) request.getAttribute("startTime");
		logger.debug("耗时:"+(System.currentTimeMillis() - start));

	}
}
