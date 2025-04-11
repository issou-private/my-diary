package com.example.serverapp.web.rest;

import com.example.serverapp.persistence.entity.Diary;
import com.example.serverapp.service.DiaryService;

import com.example.serverapp.web.response.DiaryResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
@RestController
@RequestMapping("/diary")
public class DiaryRestController {
    private final DiaryService diaryService;

    public DiaryRestController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping
    public List<DiaryResponse> findAll() {
        List<Diary> diaryList = diaryService.findAll();
        List<DiaryResponse> diaryResponseList = new ArrayList<>();
        for (Diary diary : diaryList) {
            DiaryResponse diaryResponse = new DiaryResponse(
                    diary.getId(), diary.getUserId(),
                    diary.getPostDate(), diary.getComment()
                    );
            diaryResponseList.add(diaryResponse);
        }
        return diaryResponseList;
    }

    @GetMapping("/{id}")
    public DiaryResponse findById(@PathVariable Integer id){
        Diary diary = diaryService.findById(id);
        DiaryResponse diaryResponse = new DiaryResponse(
                diary.getId(),
                diary.getUserId(),
                diary.getPostDate(),
                diary.getComment()
        );
        return diaryResponse;
    }
}