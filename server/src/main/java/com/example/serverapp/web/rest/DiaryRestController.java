package com.example.serverapp.web.rest;

import com.example.serverapp.persistence.entity.Diary;
import com.example.serverapp.service.DiaryService;
import com.example.serverapp.web.request.DiaryPostRequest;
import com.example.serverapp.web.response.DiaryResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping("/register")
    public ResponseEntity<Void> insert(@RequestBody @Validated DiaryPostRequest request) {
        Diary diary = new Diary();
        diary.setUserId(request.getUserId());
        diary.setPostDate(request.getPostDate());
        diary.setComment(request.getComment());
        diaryService.insert(diary);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(diary.getId())
        .toUri();
        return ResponseEntity.created(location).build();
    }
}