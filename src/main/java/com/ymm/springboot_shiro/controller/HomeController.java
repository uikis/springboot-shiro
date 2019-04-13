package com.ymm.springboot_shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class HomeController {
	@RequestMapping({ "/", "index" })
	public String index() {
		return "/index";
	}
 
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login() {
//		return "/login";
//	}
 
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
    /**
     * 必须是post请求
    */
    @RequestMapping("/login")
	public String login(HttpServletRequest request) {
		System.out.println("HomeController.login");
		// 登录失败从request中获取shiro处理的异常信息

		// shiroLoginFailure:就是shiro异常类的全类名
		String exception = (String) request.getAttribute("shiroLoginFailure");
		String msg = "";
		if (exception != null) {
			if (UnknownAccountException.class.getName().equals(exception)) {
				System.out.println("UnknownAccountException -->帐号不存在：");
				msg = "UnknownAccountException -->帐号不存在：";
			} else if (IncorrectCredentialsException.class.getName().equals(exception)) {
				System.out.println("IncorrectCredentialsException -- > 密码不正确：");
				msg = "IncorrectCredentialsException -- > 密码不正确：";
			} else if ("kaptchaValidateFailed".equals(exception)) {
				System.out.println("kaptchaValidateFailed -- > 验证码错误");
				msg = "kaptchaValidateFailed -- > 验证码错误";
			} else {
				msg = "else >> " + exception;
				System.out.println("else -- >" + exception);
			}
		}
//		map.put("msg", msg);// 此方法不处理登录成功,由shiro进行处理.

//        Subject subject = SecurityUtils.getSubject();
//        //创建口令对象
//        UsernamePasswordToken token = new UsernamePasswordToken("lisi", "1234");
//        //登录并检验账号的合法性
//        try {
//            subject.login(token);
//        } catch (IncorrectCredentialsException e) {
//            thrown new Exception("密码错误", e);
//        }


        return "/login";
	}

    @RequestMapping("/failed")
    public String failed() {
        return "failed";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "/admin";
    }


}