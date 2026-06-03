package com.yazh.smartspend.service;

import com.yazh.smartspend.dto.CategoryBreakdownDto;
import com.yazh.smartspend.dto.CategoryExpenseResponseDto;
import com.yazh.smartspend.dto.DashboardResponseDto;
import com.yazh.smartspend.dto.ExpenseRequestDto;
import com.yazh.smartspend.dto.ExpenseResponseDto;
import com.yazh.smartspend.dto.MonthlyExpenseResponseDto;
import com.yazh.smartspend.dto.TotalExpenseResponseDto;
import com.yazh.smartspend.entity.Expense;
import com.yazh.smartspend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yazh.smartspend.entity.User;
import com.yazh.smartspend.repository.UserRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserRepository userRepository;
    private ExpenseResponseDto mapToDto(Expense expense) {
        return new ExpenseResponseDto(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getDate(),
                expense.getNotes()
        );
    }

    public ExpenseResponseDto saveExpense( ExpenseRequestDto expenseRequestDto) {
    Expense expense = new Expense();
    User user = userRepository.findById(expenseRequestDto.getUserId()).orElse(null);

    expense.setTitle(expenseRequestDto.getTitle());
    expense.setAmount(expenseRequestDto.getAmount());
    expense.setCategory(expenseRequestDto.getCategory());
    expense.setDate(expenseRequestDto.getDate());
    expense.setNotes(expenseRequestDto.getNotes());
    expense.setUser(user);

    Expense savedExpense =  expenseRepository.save(expense);

    return mapToDto(savedExpense);
}

 public List<ExpenseResponseDto> getAllExpenses(){
  List<Expense> expenses = expenseRepository.findAll();
  List<ExpenseResponseDto> response = new ArrayList<>();
  for(Expense expense : expenses){
    response.add(mapToDto(expense));
  }
  return response;
 }

 public ExpenseResponseDto getExpenseById(Long id){
    Expense expense = expenseRepository.findById(id).orElse(null);
    if (expense != null) {
        return mapToDto(expense);
    }
    return null;
 }

 public ExpenseResponseDto updateExpense(Long id,ExpenseRequestDto expenseRequestDto){
    Expense existingExpense = expenseRepository.findById(id).orElse(null);
    if(existingExpense != null){
        existingExpense.setTitle(expenseRequestDto.getTitle());
        existingExpense.setAmount(expenseRequestDto.getAmount());
        existingExpense.setCategory(expenseRequestDto.getCategory());
        existingExpense.setDate(expenseRequestDto.getDate());
        existingExpense.setNotes(expenseRequestDto.getNotes());

        Expense savedExpense = expenseRepository.save(existingExpense);
        return mapToDto(savedExpense);
    }
    return null;
 }
 public void deleteExpense(Long id){
    expenseRepository.deleteById(id);
 }
 
 public List<ExpenseResponseDto> getExpensesByUserId(Long userId) {
   List<Expense> expenses = expenseRepository.findByUserId(userId);
   List<ExpenseResponseDto> response =   new ArrayList<>();

     for(Expense expense : expenses) {
         response.add(mapToDto(expense));
    }
    return response;
}
 public TotalExpenseResponseDto getTotalExpensesByUserId(Long userId) {
    List<Expense> expenses = expenseRepository.findByUserId(userId);
    double total = expenses.stream()
            .mapToDouble(Expense::getAmount)
            .sum();
    return new TotalExpenseResponseDto(userId,total);
  }

  public CategoryExpenseResponseDto getTotalExpensesByCategory(Long userId,String category){
    List<Expense> expenses = expenseRepository.findByUserIdAndCategory(userId,category);
    double total = expenses.stream()
    .mapToDouble(Expense::getAmount)
    .sum();
    return new CategoryExpenseResponseDto(userId,category,total);
  }

  public MonthlyExpenseResponseDto getMonthlyExpenseSummary(Long userId, Integer year, Integer month) {
    List<Expense> expenses =  expenseRepository.findByUserId(userId);
    double total = expenses.stream()
    .filter(expense ->  expense.getDate().getYear() == year && expense.getDate().getMonthValue() == month)
    .mapToDouble(Expense::getAmount)
    .sum();
    return new MonthlyExpenseResponseDto(userId,year,month,total);
  }

  public List<CategoryBreakdownDto> getCategoryBreakdown(Long userId) {
    List<Expense> expenses = expenseRepository.findByUserId(userId);
    Map<String, Double> categoryTotals = expenses.stream().collect(
                            Collectors.groupingBy(
                                    Expense::getCategory,
                                    Collectors.summingDouble(Expense::getAmount)
                            )
                    );
    List<CategoryBreakdownDto> response = new ArrayList<>();
    categoryTotals.forEach((category, total) -> response.add(new CategoryBreakdownDto(category,total)));
    return response;
   }

   public Integer getExpenseCount(Long userId) {
    return expenseRepository
            .findByUserId(userId)
            .size();
   }

   public String getTopCategory(Long userId) {
    List<Expense> expenses =   expenseRepository.findByUserId(userId);
    return expenses.stream().collect(
                    Collectors.groupingBy(Expense::getCategory,
                    Collectors.summingDouble(Expense::getAmount)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("No Expenses");
  }

  public DashboardResponseDto getDashboard(Long userId) {
    double total =  getTotalExpensesByUserId(userId).getTotalSpent();
    int count = getExpenseCount(userId);
    String topCategory =  getTopCategory(userId);
    return new DashboardResponseDto(userId,total,count,topCategory);
  }
}
