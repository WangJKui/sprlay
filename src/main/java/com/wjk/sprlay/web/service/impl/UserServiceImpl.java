package com.wjk.sprlay.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjk.sprlay.util.SprUtil;
import com.wjk.sprlay.web.mapper.UserMapper;
import com.wjk.sprlay.web.model.User;
import com.wjk.sprlay.web.service.UserService;

/**
 * 
 * @ClassName:  UserServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年1月23日 下午3:34:20   
 *
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
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
		user.setCreatetime(SprUtil.getDateTimeString());
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

}
