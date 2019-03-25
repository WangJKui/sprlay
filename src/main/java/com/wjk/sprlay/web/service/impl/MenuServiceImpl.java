package com.wjk.sprlay.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjk.sprlay.web.mapper.MenuMapper;
import com.wjk.sprlay.web.model.Menu;
import com.wjk.sprlay.web.service.MenuService;

/**
 * @ClassName  MenuServiceImpl   
 * @Description 资源权限  
 * @author WangJKui
 * @date   2019年3月22日 下午2:45:10   
 */
@Transactional
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return menuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Menu record) {
		return menuMapper.insert(record);
	}

	@Override
	public int insertSelective(Menu record) {
		return menuMapper.insertSelective(record);
	}

	@Override
	public Menu selectByPrimaryKey(Integer id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Menu record) {
		return menuMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Menu record) {
		return menuMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Menu> findAllMenu() {
		return menuMapper.findAllMenu();
	}}
