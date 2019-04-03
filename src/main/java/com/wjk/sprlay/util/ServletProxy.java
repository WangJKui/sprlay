package com.wjk.sprlay.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ClassName  ServletProxy
 * @Description Servlet环境辅助类
 * @author WangJKui
 * @date   2019年4月3日 上午9:16:56
 */
public abstract class ServletProxy {

	/**
	 * 获取应用程序发布路径
	 * 
	 * @return 应用程序发布路径
	 * @since 1.0.0
	 */
	public static String getContextPath() {
		return getRequest().getContextPath();
	}
	
	/**
	 * 
	 * 功能: 获取上下文路径<br>
	 * 作者: yangjingjiang <br>
	 * 创建日期:2018年5月11日 <br>
	 * 修改者: mender <br>
	 * 修改日期: modifydate <br>
	 * @return
	 */
	public static String getServletPath() {
		return getRequest().getServletPath();
	}

	/**
	 * 获取当前请求Cookie数组
	 * 
	 * @return 请求Cookie数组
	 * @since 1.0.0
	 */
	public static Cookie[] getCookies() {
		return getRequest().getCookies();
	}

	/**
	 * 获取HttpServletRequest对象
	 * 
	 * @return HttpServletRequest对象
	 * @since 1.0.0
	 */
	public static HttpServletRequest getRequest() {
		return getRequestAttributes().getRequest();
	}

	/**
	 * 获取Servlet请求属性
	 * 
	 * @return Servlet请求属性
	 * @since 1.0.0
	 */
	public static ServletRequestAttributes getRequestAttributes() {
		return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	}

	/**
	 * 获取当前请求线程的HtppSession对象
	 * 
	 * @return HtppSession对象
	 * @since 1.0.0
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 获取SessionID
	 * 
	 * @return SessionID
	 * @since 1.0.0
	 */
	public static String getSessionId() {
		return getRequestAttributes().getSessionId();
	}

}
