package com.wjk.sprlay.web.mapper;

import java.util.List;

import com.wjk.sprlay.web.model.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    /**
     * @Title selectByUserId   
     * @Description 根据用户id查询数据  
     * @param id
     * @return      
     * List<UserRole>
     */
	List<UserRole> selectByUserId(Integer id);
}