package com.example.serverapp.web.request;

import java.time.LocalDateTime;

public class DiaryPostRequest {
    private Integer userId;
    private LocalDateTime postDate;
    private String comment;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
