package com.yazh.smartspend.controller;
import com.yazh.smartspend.entity.User;
import com.yazh.smartspend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }
}
