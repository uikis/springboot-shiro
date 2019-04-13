package com.ymm.springboot_shiro.service;

import com.ymm.springboot_shiro.dao.UserDao;
import com.ymm.springboot_shiro.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public UserInfo queryTest(String username){
        return userDao.queryTest(username);
    }

    public String queryRoles(String userName) {
        return userDao.queryRoles(userName);
    }

    public String queryPermission(String userName) {
        return userDao.queryPermission(userName);
    }
}
