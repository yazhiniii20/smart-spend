package com.yazh.smartspend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExpenseRequestDto {
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotBlank(message = "Category cannot be empty")
    private String category;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private String notes;

    @NotNull(message = "User id is required")
    private Long userId;
}
