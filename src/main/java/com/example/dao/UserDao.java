package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("select count(*) from User where user.username = (#{username})")
    public int selectByUsername(@Param("username")String username);
    @Select("select count(*) from User where user.username = (#{username}) and user.password = (#{password})")
    public int selectUser(@Param("username")String username, @Param("password")String password);
}
