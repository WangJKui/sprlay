package com.wjk.sprlay.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.wjk.sprlay.util.FormRequest;
import com.wjk.sprlay.util.ListRequest;
import com.wjk.sprlay.web.model.Role;
import com.wjk.sprlay.web.model.User;
import com.wjk.sprlay.web.service.UserService;
import com.wjk.sprlay.web.vo.ResultData;

/**
 * 
 * @ClassName  UserController   
 * @Description 用户管理
 * @author WangJKui
 * @date   2019年3月22日 下午2:42:49   
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
	 * @Title toUserList   
	 * @Description 用户列表   
	 * @param @return      
	 * @return String
	 */
	@RequestMapping("/list")
	public String toUserList() {
		return "views/user/list";
	}

	/**
	 * 
	 * @Title toUserForm   
	 * @Description 用户详情   以及  基本资料
	 * @param @param type
	 * @param @param id
	 * @param @return      
	 * @return ModelAndView
	 */
	@RequestMapping("/form")
	public ModelAndView toUserForm(HttpServletRequest request,
								   HttpServletResponse response, 
								   FormRequest freq) {
		Integer userid = freq.getParam("userid");
		String type = freq.getParam("type");

		ModelAndView mv = userService.toMenuFormByType(userid,type);
		
		return mv;
	}
	
	/**
	 * 其实 @RequestBody接收的是一个Json对象的字符串，而不是一个Json对象。
	 * 然而在ajax请求往往传的都是Json对象，后来发现用 JSON.stringify(data)的方式就能将对象变成字符串。
	 * 同时ajax请求的时候也要指定dataType: "json",contentType:"application/json" 
	 * 这样就可以轻易的将一个对象或者List传到Java端，使用@RequestBody即可绑定对象或者List.
	 * @Title addUser   
	 * @Description 添加用户 
	 * @param @param user
	 * @param @return      
	 * @return ResultData
	 */
	@ResponseBody
	@PostMapping("/add")
	public ResultData addUser(@RequestBody User user) {
	
		userService.insertSelective(user);
		
		return ResultData.ok();
	}

	/**
	 * 
	 * @Title updateUser   
	 * @Description 修改用户信息   
	 * @param @param user
	 * @param @return      
	 * @return ResultData
	 */
	@ResponseBody
	@PostMapping("/update")
	public ResultData updateUser(@RequestBody User user) {
	
		userService.updateByPrimaryKeySelective(user);

		return ResultData.ok();
	}
	
	/**
	 * @Title deleteUser   
	 * @Description 根据id删除用户  
	 * @param @param id
	 * @param @return      
	 * @return ResultData
	 */
	@ResponseBody
	@PostMapping("/delete")
	public ResultData deleteUser(HttpServletRequest request,
								 HttpServletResponse response, 
								 FormRequest freq) {
		
		Integer userid = freq.getParam("userid");

		userService.deleteByPrimaryKey(userid);
		
		return ResultData.ok();
	}
	
	/**
	 * @Title toPassword   
	 * @Description 修改密码弹框
	 * @param @return      
	 * @return String
	 */
	@RequestMapping("/topassword")
	public String toPassword() {
		return "views/user/password";
	}
	
	/**
	 * @Title updatePassword   
	 * @Description 修改密码 
	 * @param @param request
	 * @param @param map
	 * @param @return      
	 * @return ResultData
	 */
	@ResponseBody
	@RequestMapping("/uppassword")
	public ResultData updatePassword(HttpServletRequest request,@RequestBody Map<String, String> map) {
//		request.getSession()
		logger.debug("旧密码："+map.get("oldPassword")+"新密码："+map.get("password")+"确认新密码："+map.get("rePassword"));
		
		//session获取用户信息
		
//		String mp = SprUtil.encodePassword(pwd, salt)
		
		return ResultData.ok();

	}
	
	/**
	 * @Title qureyUserByPage   
	 * @Description 分页列表显示用户信息   (模糊查询) 
	 * @param @param pageNum
	 * @param @param pageSize
	 * @param @param user
	 * @param @return      
	 * @return ResultData
	 */
	@ResponseBody
	@PostMapping("/load")
	public ResultData qureyUserByPage(
			@RequestParam(name = "page", required = false, defaultValue = "1")
			int pageNum,
			@RequestParam(name = "limit", required = false, defaultValue = "10")
			int pageSize,User user){
		
		PageInfo<User> page = userService.qureyUserByPage(pageNum,pageSize,user);
		
		return ResultData.ok(page);
	}
	
	/**
	 * @Title toUserAssignRoleList   
	 * @Description 分配角色页面list  
	 * @param id
	 * @return      
	 * ModelAndView
	 */
	@RequestMapping("/assign/{id}")
	public ModelAndView toUserAssignRoleList(@PathVariable(value = "id") Integer id) {
		ModelAndView mv = userService.toUserAssignRoleList(id);

		return mv;
	}
	
	/**
	 * @Title saveUserRole   
	 * @Description 保存用户角色数据
	 * @param userid
	 * @param user
	 * @return      
	 * ResultData
	 */
	@ResponseBody
	@PostMapping("/role")
	public ResultData saveUserRole(HttpServletRequest request,
			 					   HttpServletResponse response, 
			 					   ListRequest lreq){
		
		Integer userid = lreq.getParam("userid");
		List<Role> news = lreq.getParam("news");
		lreq.setNews(news);
		
		List<Role> list = lreq.getNews();
		
		System.out.println(userid);
		System.out.println(list.toString());
		
		return ResultData.ok();
	}
}
