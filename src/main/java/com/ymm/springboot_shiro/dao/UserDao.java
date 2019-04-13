package com.ymm.springboot_shiro.dao;

import com.ymm.springboot_shiro.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserDao {

    @Select("select * from tb_user where user_name = #{userName}")
    UserInfo queryTest(String username);

    @Select("select role from tb_user where user_name = #{userName}")
    String queryRoles(String userName);

    @Select("select permission from tb_user where user_name = #{userName}")
    String queryPermission(String userName);
}
