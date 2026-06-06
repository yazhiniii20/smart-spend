package com.yazh.smartspend.controller;

import com.yazh.smartspend.dto.CategoryBreakdownDto;
import com.yazh.smartspend.dto.ExpenseRequestDto;
import com.yazh.smartspend.dto.ExpenseResponseDto;
import com.yazh.smartspend.entity.User;
import com.yazh.smartspend.service.AuthService;
import com.yazh.smartspend.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    
    @Autowired
    private AuthService authService;
    
    @PostMapping
    public ExpenseResponseDto createExpense(@Valid @RequestBody ExpenseRequestDto expenseRequestDto,Authentication authentication) {
        String email = authentication.getName();
        User user = authService.getAuthenticatedUser(email);
        return expenseService.saveExpense(expenseRequestDto,user);
    }

    @GetMapping
    public List<ExpenseResponseDto> getAllExpenses(){
        return expenseService.getAllExpenses();
    }
    
    @GetMapping("/{id}")
    public ExpenseResponseDto getExpenseById(@PathVariable Long id){
        return expenseService.getExpenseById(id);
    }

    @PutMapping("/{id}")
    public ExpenseResponseDto updateExpense(@PathVariable Long id,@Valid @RequestBody ExpenseRequestDto expenseRequestDto){
        return expenseService.updateExpense(id,expenseRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
    }

    @GetMapping("/my")
    public List<ExpenseResponseDto> getMyExpenses(Authentication authentication) {
     String email = authentication.getName();
     User user = authService.getAuthenticatedUser(email);
     return expenseService.getMyExpenses(user.getId());
  }
   
   @GetMapping("/category-breakdown")
   public List<CategoryBreakdownDto> getCategoryBreakdown(Authentication authentication) {
    String email = authentication.getName();
    User user = authService.getAuthenticatedUser(email);
    return expenseService.getCategoryBreakdown(user.getId());
   }
}
