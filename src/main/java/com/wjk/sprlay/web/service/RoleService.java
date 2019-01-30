package com.wjk.sprlay.web.service;

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
}
