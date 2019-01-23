package com.wjk.sprlay.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wjk.sprlay.web.model.User;
import com.wjk.sprlay.web.service.UserService;
import com.wjk.sprlay.web.vo.ResultData;

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

	@Autowired
	private UserService userService;

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

	/**
	 * 
	 * @Title: qureyUserByPage   
	 * @Description: 分页列表显示用户信息    
	 * @param: @param pageNum
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@ResponseBody
	@PostMapping("/all")
	public Object qureyUserByPage(
			@RequestParam(name = "page", required = false, defaultValue = "1")
			int pageNum,
			@RequestParam(name = "limit", required = false, defaultValue = "10")
			int pageSize){
		
		PageInfo<User> page = userService.qureyUserByPage(pageNum,pageSize);
		
		return new ResultData(page);
	}
}
