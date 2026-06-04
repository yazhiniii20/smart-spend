package com.yazh.smartspend.service;

import com.yazh.smartspend.dto.CurrentUserResponseDto;
import com.yazh.smartspend.dto.LoginRequestDto;
import com.yazh.smartspend.dto.LoginResponseDto;
import com.yazh.smartspend.entity.User;
import com.yazh.smartspend.repository.UserRepository;
import com.yazh.smartspend.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public LoginResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            return new LoginResponseDto("Invalid email or password");
        }
        boolean matches =  passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!matches) {
            return new LoginResponseDto( "Invalid email or password");
        }
        String token =  jwtService.generateToken(user.getEmail());
        return new LoginResponseDto(token);
    }

    public CurrentUserResponseDto getCurrentUser(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if(user == null){
           return null;
        }
        return new CurrentUserResponseDto(user.getId(),user.getName(),user.getEmail(),user.getRole());
   }

   public User getAuthenticatedUser(String email) {
     return userRepository.findByEmail(email).orElse(null);
  }
}
