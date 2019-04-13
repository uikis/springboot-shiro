package com.ymm.springboot_shiro.realm;

import com.ymm.springboot_shiro.pojo.UserInfo;
import com.ymm.springboot_shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
 
/**
 * 身份校验核心类
 * 
 * @author Administrator
 *
 */
public class MyShiroRealm extends AuthorizingRealm {
 
	@Resource
	private UserService userService;
 
	/**
	 * 认证信息(身份验证) Authentication 是用来验证用户身份
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
		// 获取用户的输入帐号
		String username = (String) token.getPrincipal();
		System.out.println(token.getCredentials());
		// 通过username从数据库中查找 User对象，如果找到，没找到.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		UserInfo userInfo = userService.queryTest(username);//****
		System.out.println("----->>userInfo=" + userInfo);
		if (userInfo == null) {
			return null;
		}
 
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, // 用户名
				"9f4505bb4878774c6f855a3729278617", // 密码
				ByteSource.Util.bytes(username), // salt=username
				getName() // realm name
		);
		return authenticationInfo;
	}
 
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
			//获得主键对象
			String userName = (String) principals.getPrimaryPrincipal();
			//调用服务查出所有角色
			String role = userService.queryRoles(userName);//从数据库中得到角色
			String permission= userService.queryPermission(userName);//从数据库中得到权限
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();//创建封装的角色权限对象
			authorizationInfo.addRole(role);//添加角色
			authorizationInfo.addStringPermission(permission);//添加权限

			return authorizationInfo;
		}
	
}