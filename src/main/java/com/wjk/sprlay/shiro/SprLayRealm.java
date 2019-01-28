package com.wjk.sprlay.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wjk.sprlay.web.model.User;
import com.wjk.sprlay.web.service.UserService;

/**
 * 
 * @ClassName:  SprRealm   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: WangJKui
 * @date:   2019年1月18日 上午9:16:44   
 *
 */
public class SprLayRealm extends AuthorizingRealm {

	private static Logger logger = LoggerFactory.getLogger(SprLayRealm.class); 

	@Autowired
	private UserService userService;
	
	/**
	 * 授权查询, 进行鉴权但缓存中无用户的授权信息时调用
	 * @see 经测试:本例中该方法的调用时机为需授权资源被访问时 
	 * @see 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
	 * @see 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache 
	 * @see 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache  
	 * <p>Title: doGetAuthorizationInfo</p>   
	 * <p>Description: </p>   
	 * @param pc
	 * @return   
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {

		String username = (String) pc.getPrimaryPrincipal();

		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		
		//模拟角色
		List<String> roles= new ArrayList<>();
		roles.add("admin");

		//模拟权限
		List<String> permissions = new ArrayList<>();
		permissions.add("add");
		permissions.add("delete");
		permissions.add("update");
		permissions.add("query");

		simpleAuthorInfo.addRoles(roles);
		simpleAuthorInfo.addStringPermissions(permissions);
		
		logger.debug("当前授权查询者："+username);
		
		return simpleAuthorInfo;
	}

	/**
	 * 认证,登录时调用
	 * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时 
	 *  1、检查提交的进行认证的令牌信息
	 *  2、根据令牌信息从数据源(通常为数据库)中获取用户信息
	 *  3、对用户信息进行匹配验证。
	 *  4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例。
	 *  5、验证失败则抛出AuthenticationException异常信息
	 * <p>Title: doGetAuthenticationInfo</p>   
	 * <p>Description: </p>   
	 * @param token
	 * @return
	 * @throws AuthenticationException   
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		
		String username = (String)token.getPrincipal();  				//得到用户名
		String password = new String((char[])token.getCredentials()); 	//得到密码

		User user = userService.selectByUserName(username);
		
//		UsernamePasswordToken token11 = (UsernamePasswordToken)token;  

		logger.debug("当前登录认证者："+username+",密码："+password);

		if(user!=null && null != user.getUsername() && null != user.getPassword()){
			
			logger.debug("数据库账号密码："+user.getUsername()+",密码："+user.getPassword());

			//加盐
			ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername()+user.getId());
			
			return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),credentialsSalt, getName());
		}else{
			return null;
		}
		
	}

}
