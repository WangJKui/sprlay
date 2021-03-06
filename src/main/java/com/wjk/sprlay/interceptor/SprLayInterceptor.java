package com.wjk.sprlay.interceptor;


import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
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
			request.setAttribute("startTime", System.currentTimeMillis());
			
			StringBuilder sb = new StringBuilder();
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> params = new HashMap<String, Object>();
			
			String claz = "";
			String meth = "";
			
			if (handler instanceof HandlerMethod) {
				Method method = ((HandlerMethod) handler).getMethod();
				claz = method.getDeclaringClass().getName();
				meth = method.getName();
			}

			sb.append(request.getRequestURI()).append(" start...\r\n");
			map.put("mapping", claz + "(" + meth + ")");

			Enumeration<String> enums = request.getParameterNames();
			while (enums.hasMoreElements()) {
				String param = enums.nextElement();

				String val = String.join("",request.getParameterValues(param));
				if (param.equals("json")) {
					params.put(param, JSON.parse(val));
				} else {
					params.put(param, val);
				}
			}
			
			map.put("params", params);

			if (log.isInfoEnabled()){
				log.info("{} start...\r\n{}", request.getRequestURI(), JSON.toJSONString(map, true));
			}
		}
		
		return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
			throws Exception {

		if (log.isInfoEnabled()) {
			long endTime = System.currentTimeMillis();
			long startTime = endTime;

			Object objStartTime = request.getAttribute("startTime");
			if (objStartTime != null) {
				startTime = (Long) objStartTime;
			}

			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("onlines", HttpSessionListenerWrapper.getOnlines());
			map.put("timespan", endTime - startTime);

			if (log.isInfoEnabled()) {
				log.info("{} completed\r\n{}\r\n", request.getRequestURI(), JSON.toJSONString(map, false));
			}
		}

	}
}
