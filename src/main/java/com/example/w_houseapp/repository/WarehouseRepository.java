package com.example.w_houseapp.repository;

import com.example.w_houseapp.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {

    Page<Warehouse> findAllByNameContainingIgnoreCase(String name,Pageable pageable);
}
