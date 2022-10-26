package com.example.w_houseapp.repository;

import com.example.w_houseapp.entity.Input_Product;
import com.example.w_houseapp.entity.Output_Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutputProductRepository extends JpaRepository<Output_Product, UUID> {
}
