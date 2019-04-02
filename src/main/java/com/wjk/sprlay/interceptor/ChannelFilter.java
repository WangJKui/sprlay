//package com.wjk.sprlay.interceptor;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @ClassName  ChannelFilter
// * @Description ③过滤器Filter，用来把request传递下去
// * @author WangJKui
// * @date   2019年3月29日 下午3:33:00
// */
//@WebFilter(urlPatterns = "/*",filterName = "channelFilter")
//public class ChannelFilter implements Filter {
//	
//    private static Logger log = LoggerFactory.getLogger(ChannelFilter.class); 
//
//	@Override
//	public void destroy() {
//		log.info("ChannelFilter-->destroy");
//	}
//
//	@Override
//	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//			throws IOException, ServletException {
//		SprHttpServletRequestWrapper requestWrapper = null;
//		if(servletRequest instanceof HttpServletRequest) {
//			requestWrapper = new SprHttpServletRequestWrapper((HttpServletRequest) servletRequest);
//		}
//		if(requestWrapper == null) {
//			filterChain.doFilter(servletRequest, servletResponse);
//		} else {
//			filterChain.doFilter(requestWrapper, servletResponse);
//		}
//
//	}
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		log.info("ChannelFilter-->init");
//
//	}
//
//}
