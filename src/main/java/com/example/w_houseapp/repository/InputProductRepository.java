package com.example.w_houseapp.repository;

import com.example.w_houseapp.entity.Input_Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InputProductRepository extends JpaRepository<Input_Product, UUID> {
}
