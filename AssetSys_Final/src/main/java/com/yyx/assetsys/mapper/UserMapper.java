package com.yyx.assetsys.mapper;

import com.yyx.assetsys.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {


    @Select("select * from tb_sys_user where username = #{userame}")
    User findUserByName(String userName);


    @Insert("insert into tb_sys_user(username,password,name,remark,role)values(#{username},#{password},#{name},#{remark},3)")
    void registerUser(User user);


    @Select("select * from tb_sys_user")
    List<User> findAllUser();
    @Update("update tb_sys_user set username=#{username},password=#{password},name=#{name},remark=#{remark},role=#{role} where id=#{id}")
    void updateUser(User user);
}

