package com.ymm.springboot_shiro.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Aplo {

    public static void main(String[] args) {
        //使用md5加密算法得到账号密码
        String userName = "lisi";
        SimpleHash md5 = new SimpleHash("MD5", "1234",  ByteSource.Util.bytes(userName),2);
        System.out.println(md5);
    }
}
