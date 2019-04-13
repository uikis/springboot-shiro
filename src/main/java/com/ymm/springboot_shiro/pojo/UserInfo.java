package com.ymm.springboot_shiro.pojo;

import lombok.Data;

@Data
public class UserInfo {

    private String userName;
    private String password;
    private String role;
    private String permission;
}
