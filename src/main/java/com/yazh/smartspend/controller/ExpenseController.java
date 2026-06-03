package com.yazh.smartspend.controller;

import com.yazh.smartspend.dto.DashboardResponseDto;
import com.yazh.smartspend.dto.ExpenseRequestDto;
import com.yazh.smartspend.dto.ExpenseResponseDto;
import com.yazh.smartspend.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ExpenseResponseDto createExpense(@Valid @RequestBody ExpenseRequestDto expenseRequestDto) {
        return expenseService.saveExpense(expenseRequestDto);
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
}
