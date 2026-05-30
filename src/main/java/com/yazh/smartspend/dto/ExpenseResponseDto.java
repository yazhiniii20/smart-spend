package com.yazh.smartspend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ExpenseResponseDto {    
    private Long id;
    private String title;
    private Double amount;
    private String category;
    private LocalDate date;
    private String notes;
}
