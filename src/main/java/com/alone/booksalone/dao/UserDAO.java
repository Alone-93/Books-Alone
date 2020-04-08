package com.alone.booksalone.dao;

import com.alone.booksalone.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDAO {
    @Insert({"INSERT INTO user(name,email,password)VALUES(#{user.name},#{user.email},#{user.password})"})
    int addUser(@Param("user") User user);
    @Select({"SELECT id,name,email,password FROM user where id=#{id}"})
    User selectById(@Param("id") int id);
    @Select({"SELECT id,name,email,password FROM user where name=#{name}"})
    User selectByName(String name);
    @Select({"SELECT id,name,email,password FROM user where email=#{email}"})
    User selectByEmail(String email);
    @Update({"UPDATE user set password=#{user.password} where id=#{user.id}"})
    void updatePassword(User user);
}
