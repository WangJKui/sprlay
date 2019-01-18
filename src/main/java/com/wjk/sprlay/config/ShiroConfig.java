package com.wjk.sprlay.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wjk.sprlay.shiro.SprLayRealm;


/**
 * 
 * @ClassName:  ShiroConfig   
 * @Description: Shiro配置
 * @author: WangJKui
 * @date:   2019年1月16日 下午3:36:22   
 */
@Configuration
public class ShiroConfig {

	/**
	 * 
	 * @Title: realm   
	 * @Description: 注入自定义的realm，告诉shiro如何获取用户信息来做登录认证和授权
	 * @param: @return      
	 * @return: Realm      
	 * @throws
	 */
	@Bean
	public Realm realm() {
		return new SprLayRealm();
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiro = new ShiroFilterFactoryBean();
		// 必须设置SecuritManager
		shiro.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiro.setLoginUrl("/login/tologin");
		// 登录成功后要跳转的链接
		shiro.setSuccessUrl("/index");
		// 未授权界面;
		shiro.setUnauthorizedUrl("/403");
		// 拦截器
		Map<String, String> chain = new LinkedHashMap<>();
		// 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
		// 设置哪些请求可以匿名访问
		chain.put("/login/**", "anon");
		chain.put("/js/**", "anon");
		chain.put("/css/**", "anon");
		chain.put("/images/**", "anon");
		chain.put("/layui/**", "anon");
		chain.put("/fonts/**", "anon");

		// authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
		chain.put("/**", "authc");

		shiro.setFilterChainDefinitionMap(chain);

		return shiro;
	}
	/**
	 * 
	 * @Title: shiroFilterChainDefinition   
	 * @Description: 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录
	 * 					这里只做鉴权，不做权限控制，因为权限用注解来做。
	 * @param: @return      
	 * @return: ShiroFilterChainDefinition      
	 * @throws
	 */
	/*@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        //不需要在此处配置权限页面,因为上面的ShiroFilterFactoryBean已经配置过,但是此处必须存在,因为shiro-spring-boot-web-starter或查找此Bean,没有会报错
		return new DefaultShiroFilterChainDefinition();
	}*/

	/*@Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(realm());
        return securityManager;
    }*/
}
