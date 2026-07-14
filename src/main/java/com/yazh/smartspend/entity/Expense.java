package com.yazh.smartspend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import com.yazh.smartspend.entity.User;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Getter
@Setter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Positive(message="Amount must be greater than 0")
    private double amount;
    @NotBlank(message = "Category cannot be blank")
    private String category;
    private LocalDate date;
    private String notes;
    @ManyToOne
    @JoinColumn(name = "user_id")//means - create foreign key column inside expenses table
    private User user;
}
