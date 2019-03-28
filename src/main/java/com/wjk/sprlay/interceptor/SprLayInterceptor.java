package com.wjk.sprlay.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

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
	
    private static Logger log = LoggerFactory.getLogger(SprLayInterceptor.class); 

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if (log.isInfoEnabled()) {
			log.info("getRequestURL："+request.getRequestURL());
			log.info("getServerName："+request.getServerName());
			log.info("getServerPort："+request.getServerPort());
			log.info("getContextPath："+request.getContextPath());
			log.info("getServletPath："+request.getServletPath());
			//page=1&limit=10 get请求显示
			log.info("getQueryString："+request.getQueryString());
			request.setAttribute("startTime", System.currentTimeMillis());
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {

		if (log.isInfoEnabled()) {
			Long start = (Long) request.getAttribute("startTime");
			log.info("耗时:"+(System.currentTimeMillis() - start));
			
			if(modelAndView != null) {
				log.info("response data was wrote: \r\n{}" ,  JSON.toJSONString(modelAndView.toString(), true));
			}
		}
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
			throws Exception {

		if (log.isInfoEnabled()) {
			Long start = (Long) request.getAttribute("startTime");
			log.info("耗时:"+(System.currentTimeMillis() - start));
		}
	

	}
}
