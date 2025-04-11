package com.example.serverapp.service.impl;

import com.example.serverapp.persistence.entity.Diary;
import com.example.serverapp.persistence.mapper.DiaryMapper;
import com.example.serverapp.service.DiaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService {
    private final DiaryMapper diaryMapper;

    public DiaryServiceImpl(DiaryMapper diaryMapper) {
        this.diaryMapper = diaryMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Diary> findAll() {
        return diaryMapper.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Diary findById(Integer id) {
        return diaryMapper.findById(id);
    }

    @Override
    @Transactional
    public void insert(Diary diary) {
        diaryMapper.insert(diary);
    }
}
