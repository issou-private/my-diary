package com.example.serverapp.web.rest;

import com.example.serverapp.persistence.entity.User;
import com.example.serverapp.service.UserService;


import com.example.serverapp.web.request.UserRequest;

import com.example.serverapp.web.response.UserResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
@RestController
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> findAll() {
        List<User> userList = userService.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : userList) {
            UserResponse userResponse = new UserResponse(
                    user.getId(), user.getUserName(),
                    user.getPassword(), user.getJoinDate()
                    );
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Integer id){
        User user = userService.findById(id);
        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getJoinDate()
        );
        return userResponse;
    }

    @PostMapping
    public LoginUserResponse insert(@RequestBody @Validated UserRequest request){
        try{
            User user = new User();
            user.setUserName(request.getUserName());
            user.setPassword(request.getPassword());
            user.setJoinDate(request.getJoinDate());
            userService.insert(user);

        }catch (Exception e){
            System.out.println(e);
        }

        Integer id = userService.login(request.getEmail(), request.getPassword());
        LoginUserResponse loginUserResponse = new LoginUserResponse(id);
        return loginUserResponse;

    }

}