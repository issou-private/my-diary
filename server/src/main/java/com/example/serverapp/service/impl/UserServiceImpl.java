package com.example.serverapp.service.impl;

import com.example.serverapp.persistence.entity.User;
import com.example.serverapp.persistence.mapper.UserMapper;
import com.example.serverapp.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    @Transactional
    public void insert(User user) {
        userMapper.insert(user);
    }
}