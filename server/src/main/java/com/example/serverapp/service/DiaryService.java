package com.example.serverapp.service;

import com.example.serverapp.persistence.entity.Diary;
import java.util.List;

public interface DiaryService {
    Diary findById(Integer id);
    List<Diary> findAll();
    void insert(Diary diary);
}
