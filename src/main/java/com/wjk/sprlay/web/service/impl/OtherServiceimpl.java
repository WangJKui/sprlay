package com.wjk.sprlay.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjk.sprlay.web.service.OtherService;

@Transactional
@Service
public class OtherServiceimpl implements OtherService {

	public void mother() {
		System.out.println("mother1");
		int w = 2/0;
		System.out.println("mother2");

	}
}
