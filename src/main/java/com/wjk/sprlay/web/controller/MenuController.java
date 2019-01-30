package com.wjk.sprlay.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjk.sprlay.web.model.Menu;
import com.wjk.sprlay.web.service.MenuService;
import com.wjk.sprlay.web.vo.ResultData;


/**
 * 
 * @ClassName:  MenuController   
 * @Description:资源权限
 * @author: WangJKui
 * @date:   2019年1月28日 下午3:15:08   
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * 
	 * @Title: toList   
	 * @Description: list页面 
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/list")
	public String toList() {
		return "views/menu/list";
	}
	
	@ResponseBody
	@GetMapping("/load")
	public ResultData findAllMenu() {
		
		List<Menu> list = menuService.findAllMenu();
		
		return new ResultData(list);
	}
	
}
