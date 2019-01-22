package com.wjk.sprlay.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName:  UserController   
 * @Description:用户管理
 * @author: WangJKui
 * @date:   2019年1月21日 下午5:11:14   
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class); 

	/**
	 * 
	 * @Title: toUserList   
	 * @Description: 用户列表
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/list")
	public String toUserList() {
		return "views/user/list";
	}
	
	/**
	 * 
	 * @Title: toUserForm   
	 * @Description: 用户详情   以及  基本资料
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/form")
	public String toUserForm() {
		return "views/user/form";
	}
	
	
	/**
	 * 
	 * @Title: toUserPassword   
	 * @Description: 修改密码
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/password")
	public String toUserPassword() {
		return "views/user/password";
	}
}
