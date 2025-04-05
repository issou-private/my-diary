package com.example.serverapp.persistence.mapper;

import com.example.serverapp.persistence.entity.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("SELECT * FROM users")
    List<User> findAll();

    @Insert("INSERT INTO users (userName, password, joinDate) VALUES (#{userName}, #{password}, #{joinDate})")
    void insert(User user);

    @Update("UPDATE users SET userName = #{userName}, password = #{password}, joinDate = #{joinDate} WHERE id = #{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void delete(@Param("id") Integer id);
}