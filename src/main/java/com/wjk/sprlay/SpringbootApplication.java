package com.wjk.sprlay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 
 * @ClassName:  SpringbootApplication   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年1月18日 上午9:09:05   
 *
 */
@SpringBootApplication
public class SpringbootApplication {


	/**
	 * application-dev.properties：用于开发环境
		application-test.properties：用于测试环境
		application-prod.properties：用于生产环境
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}