package com.wjk.sprlay.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName  TestController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author WangJKui
 * @date   2019年3月26日 上午8:34:39
 */
@Controller
@RequestMapping("/test")
public class TestController {


	/**
	 * 测试treeTable
	 * @Title treeTable   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @param @return      
	 * @return String
	 */
	@RequestMapping("/treetable")
	public String treeTable() {
		return "views/test/treeTable";
	}
	/**
	 * 测试treeGrid
	 * @Title treeGrid   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @param @return      
	 * @return String
	 */
	@RequestMapping("/treegrid")
	public String treeGrid() {
		return "views/test/treeGrid";
	}
}
