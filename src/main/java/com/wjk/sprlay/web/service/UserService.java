package com.wjk.sprlay.web.service;

import com.github.pagehelper.PageInfo;
import com.wjk.sprlay.web.model.User;

public interface UserService {

	int deleteByPrimaryKey(Integer id);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);
    
    PageInfo<User> qureyUserByPage(int pageNum, int pageSize);

}
