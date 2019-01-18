package com.wjk.sprlay.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:  HelloController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年1月18日 上午9:05:04   
 *
 */
@RestController
@RequestMapping("/hello")
public class HelloController {


	@RequestMapping("/index.do")
	public String index() {
		return "Hello World";
	}
}
