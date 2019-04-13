//package com.ymm.springboot_shiro.controller;
//
//import com.ymm.springboot_shiro.service.UserService;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//public class TestController {
//
//    @Autowired
//    private UserService service;
//
////    @RequestMapping("/login")
////    public String tologin() {
////
////        return "login";
////    }
//
//    @RequestMapping("/tologin")
//    public String loginUser(String username, String password) {
//        //获取subject对象
//        Subject subject = SecurityUtils.getSubject();
//        //创建口令对象
//        UsernamePasswordToken token = new UsernamePasswordToken("lisi", "1234");
//        //登录并检验账号的合法性
//        subject.login(token);
//        System.out.println("------------登录" + subject.isAuthenticated());
//
//        return "/tologin";
//
//    }
//
//    @RequestMapping({ "/", "index" })
//    public String index() {
//        return "/index";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login() {
//        Subject subject = SecurityUtils.getSubject();
//        //创建口令对象
//        UsernamePasswordToken token = new UsernamePasswordToken("lisi", "1234");
//        //登录并检验账号的合法性
//        subject.login(token);
//
//        return "/login";
//
//    }
//
//    @RequestMapping("/nopermission")
//    public Object noPermission(){
//
//        return "failed";
//    }
//
//    @RequestMapping("/home")
//    public String success(){
//
//        return "index";
//    }
//
//
//    @RequestMapping("/test")
//    public Object test(){
//
//        return service.queryTest("lisi");
//    }
//}
