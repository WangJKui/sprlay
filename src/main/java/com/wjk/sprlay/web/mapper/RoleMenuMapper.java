package com.wjk.sprlay.web.mapper;

import com.wjk.sprlay.web.model.RoleMenu;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);
}