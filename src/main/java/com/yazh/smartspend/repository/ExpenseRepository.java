package com.yazh.smartspend.repository;

import com.yazh.smartspend.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Long>{
    
}
