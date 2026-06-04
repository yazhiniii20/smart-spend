package com.yazh.smartspend.controller;

import com.yazh.smartspend.dto.DashboardResponseDto;
import com.yazh.smartspend.entity.User;
import com.yazh.smartspend.service.AuthService;
import com.yazh.smartspend.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
    @Autowired
    private AuthService authService;

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/dashboard")
    public DashboardResponseDto getDashboard(Authentication authentication) {
        String email = authentication.getName();
        User user = authService.getAuthenticatedUser(email);
        return expenseService.getDashboard(user.getId());
    }
}
