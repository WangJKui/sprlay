package com.wjk.sprlay.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjk.sprlay.util.SprUtil;
import com.wjk.sprlay.web.mapper.RoleMapper;
import com.wjk.sprlay.web.mapper.UserRoleMapper;
import com.wjk.sprlay.web.model.Role;
import com.wjk.sprlay.web.model.UserRole;
import com.wjk.sprlay.web.service.RoleService;

/**
 * @ClassName  RoleServiceImpl   
 * @Description 角色   
 * @author WangJKui
 * @date   2019年3月22日 下午2:45:28   
 */
@Transactional
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Role record) {
		return roleMapper.insert(record);
	}

	@Override
	public int insertSelective(Role record) {
		return roleMapper.insertSelective(record);
	}

	@Override
	public Role selectByPrimaryKey(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		return roleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		return roleMapper.updateByPrimaryKey(record);
	}

	/**
	 * 
	 * <p>Title: qureyRoleByPage</p>   
	 * <p>Description: 根据查询条件分页查询数据</p>   
	 * @param pageNum
	 * @param pageSize
	 * @param role
	 * @return   
	 * @see com.wjk.sprlay.web.service.RoleService#qureyRoleByPage(int, int, com.wjk.sprlay.web.model.Role)
	 */
	@Override
	public PageInfo<Role> qureyRoleByPage(int pageNum, int pageSize, Role role) {
		
		PageHelper.startPage(pageNum, pageSize);
		
		List<Role> list = roleMapper.selectAllRole(role);
		
		PageInfo<Role> result = new PageInfo<Role>(list);
		
		return result;
	}

	/**
	 * <p>Title toMenuFormByType</p>   
	 * <p>Description form表单页面 根据不同类型，不同的结果</p>   
	 * @param id
	 * @param type update,detail,add
	 * @return   
	 * @see com.wjk.sprlay.web.service.RoleService#toMenuFormByType(java.lang.Integer, java.lang.String)
	 */
	@Override
	public ModelAndView toMenuFormByType(Integer id, String type) {

		Role role = selectByPrimaryKey(id);
		
		ModelAndView mv = new ModelAndView();
		if("add".equals(type)) {
			role = new Role();
			role.setCtime(SprUtil.getDateTimeString());
		}
		mv.addObject("role", role);
		//update,detail,add
		mv.addObject("type", type);
		
		mv.setViewName("views/role/form");
		
		return mv;
		
	}

	/**
	 * <p>Title qureyRoleByStatus</p>   
	 * <p>Description 加载角色数据，根据用户id以及状态，分配是否选中   </p>   
	 * @param status
	 * @param userid
	 * @return   
	 * @see com.wjk.sprlay.web.service.RoleService#qureyRoleByStatus(int, java.lang.Integer)
	 */
	@Override
	public List<Role> qureyRoleByStatus(int status, Integer userid) {
		
		Role role = new Role();
		role.setStatus(status);
		
		List<Role> roles = roleMapper.selectAllRole(role);
		
		List<UserRole> users = userRoleMapper.selectByUserId(userid);

		roles.forEach(r ->{
			users.forEach(u->{
				//
				if (r.getId().equals(u.getRoleid())) {
					r.setLAY_CHECKED(true);
				}
			});
		});
		return roles;
	}
	
}
