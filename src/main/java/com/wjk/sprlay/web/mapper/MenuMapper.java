package com.wjk.sprlay.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wjk.sprlay.web.model.Menu;

public interface MenuMapper {
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
	 * 解决There is no getter for property named 'innercode' in 'class java.lang.String'
	 * <p>1,@Param("innercode")
	 * <p>2,<if test="_parameter != null">AND innercode like concat(concat(#{innercode}),"%")</if>
	 * @Title deleteByInnercode   
	 * @Description 级联删除,根据层级码
	 * @param innercode
	 * @return      
	 * int
	 */
	int deleteByInnercode(@Param("innercode") String innercode);
}