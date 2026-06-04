package com.yazh.smartspend.controller;

import com.yazh.smartspend.dto.CurrentUserResponseDto;
import com.yazh.smartspend.dto.LoginRequestDto;
import com.yazh.smartspend.dto.LoginResponseDto;
import com.yazh.smartspend.service.AuthService;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public CurrentUserResponseDto getCurrentUser(Authentication authentication) {
     String email = authentication.getName();
     return authService.getCurrentUser(email);
   }
}
