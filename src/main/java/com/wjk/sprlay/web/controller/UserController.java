package com.wjk.sprlay.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@RequestMapping("/form/{type}")
	public String toUserForm(@PathVariable(value = "type") String type) {
		return "views/user/form";
	}
	
	/**
	 * 其实 @RequestBody接收的是一个Json对象的字符串，而不是一个Json对象。
	 * 然而在ajax请求往往传的都是Json对象，后来发现用 JSON.stringify(data)的方式就能将对象变成字符串。
	 * 同时ajax请求的时候也要指定dataType: "json",contentType:"application/json" 
	 * 这样就可以轻易的将一个对象或者List传到Java端，使用@RequestBody即可绑定对象或者List.
	 * @Title: addUser   
	 * @Description: 添加用户   
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@ResponseBody
	@PostMapping("/add")
	public ResultData addUser(@RequestBody User user) {
		
		logger.debug(user.toString());
	
		userService.insertSelective(user);
		
		return new ResultData();
	}

	/**
	 * 
	 * @Title: updateUser   
	 * @Description: 修改用户信息   
	 * @param: @param user
	 * @param: @return      
	 * @return: ResultData      
	 * @throws
	 */
	@ResponseBody
	@PostMapping("/update")
	public ResultData updateUser(@RequestBody User user) {
		
		logger.debug(user.toString());
	
		userService.updateByPrimaryKeySelective(user);
		
		ResultData r = new ResultData();
		
		logger.debug(r.toString());

		return r;
	}
	/**
	 * 
	 * @Title: deleteUser   
	 * @Description: 根据id删除用户
	 * @param: @param id
	 * @param: @return      
	 * @return: ResultData      
	 * @throws
	 */
	@ResponseBody
	@PostMapping("/delete/{id}")
	public ResultData deleteUser(@PathVariable("id") Integer id) {
		
		logger.debug(id.toString());
	
		userService.deleteByPrimaryKey(id);
		
		return new ResultData();
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
	 * @Description: 分页列表显示用户信息   (模糊查询) 
	 * @param: @param pageNum
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: Object      
	 * @throws
	 */
	@ResponseBody
	@PostMapping("/all")
	public ResultData qureyUserByPage(
			@RequestParam(name = "page", required = false, defaultValue = "1")
			int pageNum,
			@RequestParam(name = "limit", required = false, defaultValue = "10")
			int pageSize,User user){
		
		PageInfo<User> page = userService.qureyUserByPage(pageNum,pageSize,user);
		
		return new ResultData(page);
	}
}
