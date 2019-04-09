package com.wjk.sprlay.web.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.wjk.sprlay.web.model.Role;

public interface RoleService {
	int deleteByPrimaryKey(Integer id);

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	/**
	 * 
	 * @Title: qureyRoleByPage   
	 * @Description: 根据查询条件分页查询数据
	 * @param: @param pageNum
	 * @param: @param pageSize
	 * @param: @param role
	 * @param: @return      
	 * @return: PageInfo<Role>      
	 * @throws
	 */
	PageInfo<Role> qureyRoleByPage(int pageNum, int pageSize, Role role);

	/**
	 * @Title toMenuFormByType   
	 * @Description form表单页面 根据不同类型，不同的结果
	 * @param id
	 * @param type update,detail,add
	 * @return      
	 * ModelAndView
	 */
	ModelAndView toMenuFormByType(Integer id, String type);

	/**
	 * @Title qureyRoleByStatus   
	 * @Description 根据状态查询   
	 * @param status
	 * @param userid 
	 * @return      
	 * List<Role>
	 */
	List<Role> qureyRoleByStatus(int status, Integer userid);
}
