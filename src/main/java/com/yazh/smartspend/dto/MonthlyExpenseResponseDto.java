package com.yazh.smartspend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MonthlyExpenseResponseDto {
    private Long userId;
    private Integer year;
    private Integer month;
    private Double totalSpent; 
}
