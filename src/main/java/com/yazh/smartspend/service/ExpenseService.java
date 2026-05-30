package com.yazh.smartspend.service;

import com.yazh.smartspend.dto.ExpenseRequestDto;
import com.yazh.smartspend.dto.ExpenseResponseDto;
import com.yazh.smartspend.entity.Expense;
import com.yazh.smartspend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
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

    expense.setTitle(expenseRequestDto.getTitle());
    expense.setAmount(expenseRequestDto.getAmount());
    expense.setCategory(expenseRequestDto.getCategory());
    expense.setDate(expenseRequestDto.getDate());
    expense.setNotes(expenseRequestDto.getNotes());

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

}
