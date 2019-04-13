package com.ymm.springboot_shiro.realm;

import com.ymm.springboot_shiro.pojo.UserInfo;
import com.ymm.springboot_shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

        @Autowired
        private UserService userService;

        @Override
        public String getName() {
        return "MyRealm";
        }

        //认证.登录
        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
            System.out.println("------------------1111");
            UsernamePasswordToken utoken =(UsernamePasswordToken) token;//获取用户输入的token
            String userName = utoken.getUsername();
            UserInfo user = userService.queryTest(userName);
            if(user== null){
                return null;
            }

            return new SimpleAuthenticationInfo(userName, "1234",getName());
            //放入shiro.调用CredentialsMatcher检验密码
        }
        //授权
        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
            System.out.println("------------------2222");
            //获得主键对象
            String userName = (String) principal.getPrimaryPrincipal();
            //调用服务查出所有角色
            String role = userService.queryRoles(userName);//从数据库中得到角色
            String permission= userService.queryPermission(userName);//从数据库中得到权限
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();//创建封装的角色权限对象
            authorizationInfo.addRole(role);//添加角色
            authorizationInfo.addStringPermission(permission);//添加权限

            return null;
        }

//        @Override
//        protected AuthenticationInfo doGetAuthenticationInfo(
//                AuthenticationToken token) throws AuthenticationException {
//            // 将AuthenticationToken对象转换成UsernamePasswordToken对象
//            UsernamePasswordToken upToken = (UsernamePasswordToken) token;
//            // 获取UsernamePasswordToken中的用户名
//            String username = upToken.getUsername();
//            // 从数据库中查询 username 对应的用户记录
//            System.out.println("从数据库中查找" + username + "的信息");
//            // 若用户的信息不存在，则抛出UnknownAccountException异常。
//            if ("unknown".equals(username)) {
//                throw new UnknownAccountException("用户不存在");
//            }
//            // 根据用户的信息进行反馈，则抛出LockedAccountException异常。
//            if ("han".equals(username)) {
//                throw new LockedAccountException("用户被锁定");
//            }
//            // 根据用户的信息来封装SimpleAuthenticationInfo对象。
//
//            // 当前 realm 对象的 name
//            String realmName = getName();
//            // 认证的实体信息。
//            Object principal = username;
//            // 密码
//            Object hashedCredentials = null;
//            if("admin".equals(username)){
//                hashedCredentials = "2abec21dc41c75c88cb87e7306c5e75f";
//            }else if("zhao".equals(username)){
//                hashedCredentials = "399503120959cd94972d6d5f3a9d4c61";
//            }
//            //盐值
//            ByteSource credentialsSalt = ByteSource.Util.bytes(username);
//            SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
//            return info;
//        }

        /**
         * 明文密码进行加密
         * @param args
         */
        public static void main(String[] args) {
            int hashIterations = 10000;//加密的次数
            Object salt = "zhao";//盐值
            Object credentials = "123456";//密码
            String hashAlgorithmName = "MD5";//加密方式
            Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,
                    salt, hashIterations);
            System.out.println("加密后的值----->" + simpleHash);
        }


    }