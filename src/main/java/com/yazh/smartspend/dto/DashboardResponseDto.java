package com.yazh.smartspend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DashboardResponseDto {
    private Long userId;

    private Double totalSpent;

    private Integer expenseCount;

    private String topCategory;
}
