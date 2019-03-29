package com.wjk.sprlay.web.mapper;

import java.util.List;

import com.wjk.sprlay.web.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * @Title selectAllRole   
     * @Description 根据查询条件分页查询数据 
     * @param role
     * @return      
     * List<Role>
     */
	List<Role> selectAllRole(Role role);
}