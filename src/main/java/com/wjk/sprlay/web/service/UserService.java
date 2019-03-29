package com.wjk.sprlay.web.service;

import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.wjk.sprlay.web.model.User;

public interface UserService {

	int deleteByPrimaryKey(Integer id);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);
    
    /**
     * 
     * @Title: qureyUserByPage   
     * @Description: 分页列表显示用户信息(模糊查询)  
     * @param: @param pageNum
     * @param: @param pageSize
     * @param: @param user
     * @param: @return      
     * @return: PageInfo<User>      
     * @throws
     */
    PageInfo<User> qureyUserByPage(int pageNum, int pageSize, User user);

    /**
     * 
     * @Title: selectByUserName   
     * @Description: 根据账号查询用信息  
     * @param: @param username
     * @param: @return      
     * @return: User      
     * @throws
     */
    User selectByUserName(String username);

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
	 * @Title toUserAssignRoleList   
	 * @Description 用户分配角色list  
	 * @param id
	 * @return      
	 * ModelAndView
	 */
	ModelAndView toUserAssignRoleList(Integer id);
}
