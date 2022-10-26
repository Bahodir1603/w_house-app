package com.example.w_houseapp.repository;

import com.example.w_houseapp.entity.Input;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InputRepository extends JpaRepository<Input, UUID> {
}
