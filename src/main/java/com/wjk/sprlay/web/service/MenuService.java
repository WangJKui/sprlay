package com.wjk.sprlay.web.service;

import java.util.List;

import com.wjk.sprlay.web.model.Menu;

public interface MenuService {

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /**
     * 
     * @Title: findAllMenu   
     * @Description: 查询所有的资源权限 
     * @param: @return      
     * @return: List<Menu>      
     * @throws
     */
    List<Menu> findAllMenu();
}
