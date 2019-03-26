package com.wjk.sprlay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wjk.sprlay.web.model.Role;
import com.wjk.sprlay.web.service.RoleService;
import com.wjk.sprlay.web.vo.ResultData;



/**
 * 
 * @ClassName  RoleController   
 * @Description 角色 
 * @author WangJKui
 * @date   2019年3月22日 下午2:42:18   
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	/**
	 * 
	 * @Title toList   
	 * @Description list页面  
	 * @param @return      
	 * @return String
	 */
	@RequestMapping("/list")
	public String toList() {
		return "views/role/list";
	}
	
	/**
	 * 
	 * @Title qureyRoleByPage   
	 * @Description 根据查询条件分页查询数据 
	 * @param @param pageNum
	 * @param @param pageSize
	 * @param @param role
	 * @param @return      
	 * @return ResultData
	 */
	@ResponseBody
	@PostMapping("/load")
	public ResultData qureyRoleByPage(
		@RequestParam(name = "page", required = false, defaultValue = "1")
		int pageNum,
		@RequestParam(name = "limit", required = false, defaultValue = "10")
		int pageSize,Role role){
	
		PageInfo<Role> page = roleService.qureyRoleByPage(pageNum,pageSize,role);
		
		return ResultData.ok(page);
	}
	
}
