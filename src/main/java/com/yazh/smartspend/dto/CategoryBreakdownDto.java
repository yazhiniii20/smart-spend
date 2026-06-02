package com.yazh.smartspend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryBreakdownDto {
    private String category;
    private Double totalSpent;
}
