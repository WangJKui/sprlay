package com.wjk.sprlay.web.controller;

import java.util.List;

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
	 * @Title toList   
	 * @Description list页面          
	 * @return String
	 */
	@RequestMapping("/list")
	public String toList() {
		return "views/role/list";
	}
	
	/**
	 * @Title qureyRoleByPage   
	 * @Description 根据查询条件分页查询数据   
	 * @param pageNum
	 * @param pageSize
	 * @param role
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
	
	/**
	 * @Title toRoleForm   
	 * @Description 角色详情页面
	 * @param type
	 * @param id
	 * @return ModelAndView     
	 */
	@RequestMapping("/form/{type}/{id}")
	public ModelAndView toRoleForm(@PathVariable(value = "type") String type,@PathVariable(value = "id") Integer id) {
		
		ModelAndView mv = roleService.toMenuFormByType(id,type);
		
		return mv;
	}
	
	/**
	 * @Title addRole   
	 * @Description 新增角色  
	 * @param role
	 * @return ResultData    
	 */
	@ResponseBody
	@PostMapping("/add")
	public ResultData addRole(@RequestBody Role role) {
	
		roleService.insertSelective(role);
		
		return ResultData.ok();
	}
	
	/**
	 * @Title updateRole   
	 * @Description 更新  
	 * @param role
	 * @return  ResultData
	 */
	@ResponseBody
	@PostMapping("/update")
	public ResultData updateRole(@RequestBody Role role) {
		
		roleService.updateByPrimaryKeySelective(role);

		return ResultData.ok();
	}
	
	/**
	 * @Title deleteRole   
	 * @Description 删除
	 * @param id
	 * @return ResultData     
	 */
	@ResponseBody
	@PostMapping("/delete/{id}")
	public ResultData deleteRole(@PathVariable("id") Integer id) {
	
		roleService.deleteByPrimaryKey(id);
		
		return ResultData.ok();
	}
	
	/**
	 * @Title qureyRoleByStatus   
	 * @Description 加载角色数据，根据用户id以及状态，分配是否选中   
	 * @param role
	 * @param userid
	 * @return      
	 * ResultData
	 */
	@ResponseBody
	@PostMapping("/loadall")
	public ResultData qureyRoleByStatus(Role role,
			@RequestParam(name = "userid", required = true) Integer userid){
	
		List<Role> list = roleService.qureyRoleByStatus(role,userid);
		
		return ResultData.ok(list);
	}
}
