package com.example.serverapp.web.response;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("all")
public class UserResponse {
    private final Integer id;
    private String userName;
    private String password;
    private LocalDate joinDate;

    public UserResponse(Integer id, String userName, String email, String password, LocalDate joinDate) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.joinDate = joinDate;
    }


    public UserResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }
}
