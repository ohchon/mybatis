package com.finotech.finalmission.mapper;

import com.finotech.finalmission.model.User;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user where username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO user (username, password) values (#{username}, #{password})")
    void save(@Param("username") String username, @Param("password") String password);

    @Update("UPDATE user SET username = #{username} WHERE password = #{password}")
    void update(String username, String password);

    @Delete("DELETE FROM user WHERE password = #{password}")
    void delete(String password);

}
