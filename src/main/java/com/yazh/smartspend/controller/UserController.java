package com.yazh.smartspend.controller;
import com.yazh.smartspend.entity.User;
import com.yazh.smartspend.service.ExpenseService;
import com.yazh.smartspend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.yazh.smartspend.dto.UserResponseDto;
import jakarta.validation.Valid;
import com.yazh.smartspend.service.AuthService;
import com.yazh.smartspend.dto.CategoryBreakdownDto;
import com.yazh.smartspend.dto.CategoryExpenseResponseDto;
import com.yazh.smartspend.dto.DashboardResponseDto;
import com.yazh.smartspend.dto.ExpenseResponseDto;
import com.yazh.smartspend.dto.MonthlyExpenseResponseDto;
import com.yazh.smartspend.dto.TotalExpenseResponseDto;
import com.yazh.smartspend.dto.UserRequestDto;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private AuthService authService;

    @PostMapping
    public UserResponseDto createUser( @Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.saveUser(userRequestDto);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id,@RequestBody User user){
        return userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
    
    @GetMapping("/{userId}/expenses")
    public List<ExpenseResponseDto> getUserExpenses(@PathVariable Long userId) {
        return expenseService.getExpensesByUserId(userId);
   }
   
   @GetMapping("/{userId}/expenses/total")
   public TotalExpenseResponseDto getTotalExpenses(@PathVariable Long userId) {
     return expenseService.getTotalExpensesByUserId(userId);
   }

   @GetMapping("/{userId}/expenses/category/{category}")
   public CategoryExpenseResponseDto getExpensesByCategory(@PathVariable Long userId,
   @PathVariable String category) {
    return expenseService.getTotalExpensesByCategory(userId,category);
   }

   @GetMapping("/{userId}/expenses/month/{year}/{month}")
   public MonthlyExpenseResponseDto getMonthlyExpenseSummary(@PathVariable Long userId, @PathVariable Integer year,
        @PathVariable Integer month) {

     return expenseService.getMonthlyExpenseSummary(userId,year,month);
   }
   
    @GetMapping("/{userId}/dashboard")
    public DashboardResponseDto getDashboard(@PathVariable Long userId) {
       return expenseService.getDashboard(userId);
   }
}
