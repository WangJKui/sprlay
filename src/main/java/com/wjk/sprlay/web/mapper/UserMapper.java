package com.wjk.sprlay.web.mapper;

import java.util.List;

import com.wjk.sprlay.web.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<User> selectAllUser();
}