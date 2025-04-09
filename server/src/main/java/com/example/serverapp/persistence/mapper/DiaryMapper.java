package com.example.serverapp.persistence.mapper;

import com.example.serverapp.persistence.entity.Diary;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DiaryMapper {

    @Select("SELECT id, user_id, post_date, comment FROM diaries ORDER BY id")
    List<Diary> findAll();

    @Select("SELECT * FROM diaries WHERE id = #{id}")
    Diary findById(@Param("id") Integer id);

    @Insert("INSERT INTO diaries (user_id, post_date, comment) VALUES (#{userId}, #{postDate}, #{comment})")
    void insert(Diary diary);

    @Delete("DELETE FROM diaries WHERE id = #{id}")
    void delete(@Param("id") Integer id);
}