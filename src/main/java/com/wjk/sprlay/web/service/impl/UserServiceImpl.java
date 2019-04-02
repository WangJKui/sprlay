package com.wjk.sprlay.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjk.sprlay.util.SprUtil;
import com.wjk.sprlay.web.mapper.UserMapper;
import com.wjk.sprlay.web.model.User;
import com.wjk.sprlay.web.service.UserService;

/**
 * @ClassName  UserServiceImpl   
 * @Description 用户
 * @author WangJKui
 * @date   2019年3月22日 下午2:45:39   
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
//	@Autowired
//	private UserRoleMapper userRoleMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public int insertSelective(User user) {
		//初始化密码为123456
		user.setPassword("123456");
		//设置当前时间
		user.setCtime(SprUtil.getDateTimeString());
		return userMapper.insertSelective(user);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int updateByPrimaryKey(User user) {
		return userMapper.updateByPrimaryKey(user);
	}

	/**
	 * 
	 * <p>Title: qureyUserByPage</p>   
	 * <p>Description: 分页列表显示用户信息(模糊查询)</p>   
	 * @param pageNum
	 * @param pageSize
	 * @return   
	 * @see com.wjk.sprlay.web.service.UserService#qureyUserByPage(int, int)
	 */
	@Override
	public PageInfo<User> qureyUserByPage(int pageNum, int pageSize, User user) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = userMapper.selectAllUser(user);
		PageInfo<User> result = new PageInfo<User>(list);
		return result;
	}

	/**
	 * 
	 * <p>Title: selectByUserName</p>   
	 * <p>Description: 根据账号查询用户信息</p>   
	 * @param username
	 * @return   
	 * @see com.wjk.sprlay.web.service.UserService#selectByUserName(java.lang.String)
	 */
	@Override
	public User selectByUserName(String username) {
		
		return userMapper.selectByUserName(username);
	}

	/**
	 * <p>Title toMenuFormByType</p>   
	 * <p>Description form表单页面 根据不同类型，不同的结果</p>   
	 * @param userid
	 * @param type update,detail,add
	 * @return   
	 * @see com.wjk.sprlay.web.service.UserService#toMenuFormByType(java.lang.Integer, java.lang.String)
	 */
	@Override
	public ModelAndView toMenuFormByType(Integer userid, String type) {

		User user = selectByPrimaryKey(userid);
		
		ModelAndView mv = new ModelAndView();
		if("add".equals(type)) {
			user = new User();
			user.setCtime(SprUtil.getDateTimeString());
			user.setSex("男");
		}
		mv.addObject("user", user);
		//update,detail,add
		mv.addObject("type", type);
		
		mv.setViewName("views/user/form");
		
		return mv;
		
	}

	/**
	 * <p>Title toUserAssignRoleList</p>   
	 * <p>Description 用户分配角色list</p>   
	 * @param id
	 * @return ModelAndView
	 * @see com.wjk.sprlay.web.service.UserService#toUserAssignRoleList(java.lang.Integer)
	 */
	@Override
	public ModelAndView toUserAssignRoleList(Integer id) {
		
//		List<UserRole> list = userRoleMapper.selectByUserId(id);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("views/user/assign_role");
		
		mv.addObject("userid",id);
		
		return mv;
	}

}
