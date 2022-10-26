package com.example.w_houseapp.repository;

import com.example.w_houseapp.entity.Category;
import com.example.w_houseapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
