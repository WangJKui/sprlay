package com.wjk.sprlay.web.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

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

    /**
     * @Title insertAndUpdateSelective   
     * @Description 新增数据以及更新数据层级码 
     * @param menu      
     * void
     */
	void insertAndUpdateSelective(Menu menu);

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
	 * @Title deleteByInnercode   
	 * @Description 级联删除,根据层级码
	 * @param id
	 * @return      
	 * int 删除条数
	 */
	int deleteByInnercode(Integer id);
}
