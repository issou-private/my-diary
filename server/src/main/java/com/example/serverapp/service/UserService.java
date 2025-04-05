package com.example.serverapp.service;

import com.example.serverapp.persistence.entity.User;
import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
    void insert(User user);
}
