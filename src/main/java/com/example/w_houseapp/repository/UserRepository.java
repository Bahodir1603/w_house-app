package com.example.w_houseapp.repository;


import com.example.w_houseapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<UserDetails> findByPhone(String phone);
    Optional<User> findByEmailAndCode(String email, String code);
}
