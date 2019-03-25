package com.wjk.sprlay.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjk.sprlay.web.mapper.RoleMapper;
import com.wjk.sprlay.web.model.Role;
import com.wjk.sprlay.web.service.RoleService;

/**
 * @ClassName  RoleServiceImpl   
 * @Description 角色   
 * @author WangJKui
 * @date   2019年3月22日 下午2:45:28   
 */
@Transactional
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Role record) {
		return roleMapper.insert(record);
	}

	@Override
	public int insertSelective(Role record) {
		return roleMapper.insertSelective(record);
	}

	@Override
	public Role selectByPrimaryKey(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		return roleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		return roleMapper.updateByPrimaryKey(record);
	}

	/**
	 * 
	 * <p>Title: qureyRoleByPage</p>   
	 * <p>Description: 根据查询条件分页查询数据</p>   
	 * @param pageNum
	 * @param pageSize
	 * @param role
	 * @return   
	 * @see com.wjk.sprlay.web.service.RoleService#qureyRoleByPage(int, int, com.wjk.sprlay.web.model.Role)
	 */
	@Override
	public PageInfo<Role> qureyRoleByPage(int pageNum, int pageSize, Role role) {
		
		PageHelper.startPage(pageNum, pageSize);
		
		List<Role> list = roleMapper.selectAllRole(role);
		
		PageInfo<Role> result = new PageInfo<Role>(list);
		
		return result;
	}
	
}
