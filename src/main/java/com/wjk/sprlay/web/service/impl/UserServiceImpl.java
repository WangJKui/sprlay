package com.wjk.sprlay.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjk.sprlay.web.mapper.UserMapper;
import com.wjk.sprlay.web.model.User;
import com.wjk.sprlay.web.service.OtherService;
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
	 * <p>Description: 分页查询用户数据</p>   
	 * @param pageNum
	 * @param pageSize
	 * @return   
	 * @see com.wjk.sprlay.web.service.UserService#qureyUserByPage(int, int)
	 */
	@Override
	public PageInfo<User> qureyUserByPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = userMapper.selectAllUser();
		PageInfo<User> result = new PageInfo<User>(list);
		return result;
	}

}
