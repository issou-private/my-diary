package com.example.serverapp.persistence.mapper;

import com.example.serverapp.persistence.entity.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select id, user_name, password, join_date from users order by id")
    List<User> findAll();
    
    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(@Param("id") Integer id);

    @Insert("INSERT INTO users (user_name, password, join_date) VALUES (#{userName}, #{password}, #{joinDate})")
    void insert(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void delete(@Param("id") Integer id);
}