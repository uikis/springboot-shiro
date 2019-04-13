package com.ymm.springboot_shiro.config;

import com.ymm.springboot_shiro.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.Map;

//@Configuration
public class ShiroConfig {
    //自定义加密算法
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(3);

        return matcher;
    }
    //注入自己重写的realm类
    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;
    }
    //给安全管理器设置reaml
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm());

        return defaultWebSecurityManager;
    }
    //配置过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        Map<String, String> filterChainDefinitiMap = new LinkedHashMap<>();
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/nopermission");
//        filterChainDefinitiMap.put("/login", "anon");
        filterChainDefinitiMap.put("/admin", "roles[admin]");
        filterChainDefinitiMap.put("/logout", "logout");
        filterChainDefinitiMap.put("/**", "authc");//表示需要认证才可以访问
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitiMap);

        return shiroFilterFactoryBean;
    }


}
