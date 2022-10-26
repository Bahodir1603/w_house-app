package com.example.w_houseapp.repository;

import com.example.w_houseapp.entity.Product_measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductMeasurementRepository extends JpaRepository<Product_measurement, UUID> {
}
