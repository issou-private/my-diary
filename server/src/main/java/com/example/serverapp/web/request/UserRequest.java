package com.example.serverapp.web.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

@SuppressWarnings("all")
public class UserRequest {

    @NotBlank
    @Size(min = 1, max = 20)
    private String userName;

    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]*$")
    private String password;

    private LocalDate joinDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getJoindate() {
        return LocalDate.now();
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = LocalDate.now();
    }
}