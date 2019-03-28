package com.wjk.sprlay.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wjk.sprlay.web.model.Menu;
import com.wjk.sprlay.web.service.MenuService;
import com.wjk.sprlay.web.vo.ResultData;


/**
 * 
 * @ClassName  MenuController   
 * @Description 资源权限  
 * @author WangJKui
 * @date   2019年3月22日 下午2:41:46   
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * 
	 * @Title toList   
	 * @Description list页面 
	 * @return String
	 */
	@RequestMapping("/list")
	public String toList() {
		return "views/menu/list";
	}
	
	/**
	 * 
	 * @Title findAllMenu   
	 * @Description 加载所有资源
	 * @return ResultData
	 */
	@ResponseBody
	@GetMapping("/load")
	public ResultData findAllMenu() {
		
		List<Menu> list = menuService.findAllMenu();
		
		return ResultData.ok(list);
	}
	
	/**
	 * @Title toMenuForm   
	 * @Description form表单页面
	 * @param type
	 * @param id
	 * @return ModelAndView   
	 */
	@RequestMapping("/form/{type}/{id}")
	public ModelAndView toMenuForm(@PathVariable(value = "type") String type,@PathVariable(value = "id") Integer id) {
		
		ModelAndView mv = menuService.toMenuFormByType(id,type);
		
		return mv;
	}
	
	/**
	 * @Title addMenu   
	 * @Description 新增   
	 * @param Menu
	 * @return ResultData     
	 */
	@ResponseBody
	@PostMapping("/add")
	public ResultData addMenu(@RequestBody Menu menu) {
	
		menuService.insertAndUpdateSelective(menu);

		return ResultData.ok();
	}
	
	/**
	 * @Title updateMenu   
	 * @Description 更新
	 * @param menu
	 * @return ResultData      
	 */
	@ResponseBody
	@PostMapping("/update")
	public ResultData updateMenu(@RequestBody Menu menu) {
	
		menuService.updateByPrimaryKeySelective(menu);

		return ResultData.ok();
	}
	
	/**
	 * @Title deleteUser   
	 * @Description 删除   
	 * @param id
	 * @return ResultData   
	 */
	@ResponseBody
	@PostMapping("/delete/{id}")
	public ResultData deleteUser(@PathVariable("id") Integer id) {
		
		//级联删除,根据层级码
		int num = menuService.deleteByInnercode(id);
		
		return ResultData.ok(num);
	}
}
