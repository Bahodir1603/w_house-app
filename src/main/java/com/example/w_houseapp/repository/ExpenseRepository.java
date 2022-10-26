package com.example.w_houseapp.repository;

import com.example.w_houseapp.entity.Expense;
import com.example.w_houseapp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
}
