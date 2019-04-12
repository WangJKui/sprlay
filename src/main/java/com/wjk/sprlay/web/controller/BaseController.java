package com.wjk.sprlay.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.wjk.sprlay.util.SprUtil;
import com.wjk.sprlay.web.ListRequest;
import com.wjk.sprlay.web.ListResponse;

/**
 * @ClassName  BaseController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author WangJKui
 * @date   2019年4月10日 下午3:59:46
 */
public abstract class BaseController {

	/**
	 * @Title writeJsonList   
	 * @Description 输出JSON格式的列表响应数据对象   
	 * @param response
	 * @param lres      
	 * void
	 */
	public void writeJsonList(HttpServletResponse response, ListResponse lres) {
		SprUtil.writeJson(response, lres);		
	}

	/**
	 * @Title printModelAndView   
	 * @Description 打印 
	 * @param mav      
	 * void
	 */
	public void printModelAndView(ModelAndView mav) {
		SprUtil.printModelAndViewLog(mav);
	}

	/**
	 * @param <T>
	 * @Title setListRequestNews   
	 * @Description 解析请求参数 
	 * @param lreq
	 * @param clz      
	 * void
	 */
	public <T> void setListRequestNews(ListRequest lreq, Class<T> clz) {
		SprUtil.setListRequestNews(lreq,clz);
	}
	
}
