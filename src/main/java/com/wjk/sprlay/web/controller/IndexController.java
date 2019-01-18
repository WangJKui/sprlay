package com.wjk.sprlay.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName:  IndexController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年1月18日 上午9:46:46   
 *
 */
@Controller
public class IndexController {
	
	private static Logger logger = LoggerFactory.getLogger(IndexController.class); 

	/**
	 * 
	 * @Title: index   
	 * @Description: 解决登录之后地址栏http://localhost:8181/spr/login/login ,
	 * 				未改变，可以刷新重新登录
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/index")
	public String index(ModelMap map) {
		return "index";
	}
	
	@RequestMapping("/test")
	public String testMenu() {
		return "views/test/menu";
	}
}
