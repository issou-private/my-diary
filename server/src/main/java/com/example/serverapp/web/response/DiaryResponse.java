package com.example.serverapp.web.response;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("all")
public class DiaryResponse {
    private final Integer id;
    private Integer userId;
    private LocalDateTime postDate;
    private String comment;

    public DiaryResponse(Integer id, Integer userId, LocalDateTime postDate, String comment) {
        this.id = id;
        this.userId = userId;
        this.postDate = postDate;
        this.comment = comment;
    }

    public DiaryResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    public Integer getUserId() {
        return userId;
    }
    public LocalDateTime getPostDate() {
        return postDate;
    }
    public String getComment() {
        return comment;
    }
}
