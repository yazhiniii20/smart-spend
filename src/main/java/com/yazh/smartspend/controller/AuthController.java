package com.yazh.smartspend.controller;

import com.yazh.smartspend.dto.CurrentUserResponseDto;
import com.yazh.smartspend.dto.LoginRequestDto;
import com.yazh.smartspend.dto.LoginResponseDto;
import com.yazh.smartspend.service.AuthService;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.yazh.smartspend.service.UserService;
import com.yazh.smartspend.dto.UserRequestDto;
import com.yazh.smartspend.dto.UserResponseDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public CurrentUserResponseDto getCurrentUser(Authentication authentication) {
     String email = authentication.getName();
     return authService.getCurrentUser(email);
   }

   @PostMapping("/register")
    public UserResponseDto register(@Valid @RequestBody UserRequestDto request) {
      return userService.saveUser(request);
  }
}
