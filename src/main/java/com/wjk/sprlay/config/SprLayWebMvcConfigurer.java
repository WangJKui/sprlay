package com.wjk.sprlay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wjk.sprlay.interceptor.SprLayInterceptor;

/**
 * 
 * @ClassName:  SprWebMvcConfigurer   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年1月18日 上午9:07:38   
 *
 */
@Configuration
public class SprLayWebMvcConfigurer implements WebMvcConfigurer{

	@Autowired
	private SprLayInterceptor sprLayInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 可添加多个
		registry.addInterceptor(sprLayInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/js/**")
		.excludePathPatterns("/css/**")
		.excludePathPatterns("/images/**")
		.excludePathPatterns("/layui/**")
		.excludePathPatterns("/fonts/**");

	}

	 /**
	  * 添加静态资源文件，外部可以直接访问地址
	  * <p>Title: addResourceHandlers</p>   
	  * <p>Description: </p>   
	  * @param registry   
	  */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    
}
