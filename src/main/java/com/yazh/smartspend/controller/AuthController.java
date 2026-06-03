package com.yazh.smartspend.controller;

import com.yazh.smartspend.dto.LoginRequestDto;
import com.yazh.smartspend.dto.LoginResponseDto;
import com.yazh.smartspend.service.AuthService;
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
}
