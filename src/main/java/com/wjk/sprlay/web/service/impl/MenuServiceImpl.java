package com.wjk.sprlay.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.wjk.sprlay.util.SprUtil;
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
	}

	/**
	 * 新增数据以及更新数据层级码 
	 * <p>Title insertAndUpdateSelective</p>   
	 * <p>Description </p>   
	 * @param menu   
	 * @see com.wjk.sprlay.web.service.MenuService#insertAndUpdateSelective(com.wjk.sprlay.web.model.Menu)
	 */
	@Override
	public void insertAndUpdateSelective(Menu menu) {
		
		insertSelective(menu);
		
		Menu up = new Menu();
		up.setId(menu.getId());
		up.setInnercode(menu.getInnercode()+menu.getId()+"-");
		//更新层级码
		updateByPrimaryKeySelective(up);
	}

	/**
	 * <p>toMenuFormByType</p>   
	 * <p>form表单页面 根据不同类型，不同的结果</p>   
	 * @param id
	 * @param type update,detail,add
	 * @return   
	 * @see com.wjk.sprlay.web.service.MenuService#toMenuFormByType(java.lang.Integer, java.lang.String)
	 */
	@Override
	public ModelAndView toMenuFormByType(Integer id,String type) {
		
		Menu menu = selectByPrimaryKey(id);
		
		ModelAndView mv = new ModelAndView();
		
		if("add".equals(type)) {
			//顶级节点，也就是pid=0,id=0
			String innercode = menu == null ? "-0-":menu.getInnercode();
			
			menu = new Menu();
			menu.setPid(id);
			menu.setType(1);
			//先设置为父级码
			menu.setInnercode(innercode);
			menu.setCtime(SprUtil.getDateTimeString());
		}
		mv.addObject("menu", menu);
		//update,detail,add
		mv.addObject("type", type);
		
		mv.setViewName("views/menu/form");
		
		return mv;
	}

	/**
	 * <p>Title deleteByInnercode</p>   
	 * <p>Description 级联删除,根据层级码</p>   
	 * @param id
	 * @return   
	 * @see com.wjk.sprlay.web.service.MenuService#deleteByInnercode(java.lang.Integer)
	 */
	@Override
	public int deleteByInnercode(Integer id) {

		Menu menu = selectByPrimaryKey(id);
		
		if(menu == null || SprUtil.isEmpty(menu.getInnercode())) {
			return 0;
		}
		
		return menuMapper.deleteByInnercode(menu.getInnercode());
	}
	
}
