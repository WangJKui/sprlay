package com.wjk.sprlay;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @MapperScan("com.wjk.sprlay.web.mapper") mapper接口上不需要在使用注解
 * @EnableTransactionManagement 事物启动
 * @ClassName:  SpringbootApplication   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年1月18日 上午9:09:05   
 *
 */
@MapperScan("com.wjk.sprlay.web.mapper")
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