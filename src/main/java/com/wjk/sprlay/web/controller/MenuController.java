package com.wjk.sprlay.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * @ClassName:  MenuController   
 * @Description:菜单 
 * @author: WangJKui
 * @date:   2019年1月28日 下午3:15:08   
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {


	@RequestMapping("/list")
	public String toList() {
		return "views/menu/list";
	}
}
