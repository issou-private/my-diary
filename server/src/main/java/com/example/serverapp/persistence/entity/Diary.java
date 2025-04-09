package com.example.serverapp.persistence.entity;
import java.time.LocalDateTime;

public class Diary {
    private Integer id;
    private Integer userId;
    private LocalDateTime postDate;
    private String comment;
    //private Byte[] picture;   

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", userId=" + userId +
                ", postDate=" + postDate +
                ", comment='" + comment + '\'' +
                '}';
    }
}

