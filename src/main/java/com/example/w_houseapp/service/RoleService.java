package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.RoleDto;
import com.example.w_houseapp.entity.Role;
import com.example.w_houseapp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public ApiResponse save(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        role.setActive(roleDto.isActive());

        Role save = roleRepository.save(role);
        return ApiResponse.builder().message("Added!").success(true).data(save).build();

    }

    public ApiResponse getAll() {
        List<Role> all = roleRepository.findAll();
        return ApiResponse.builder().message("Mana").success(true).data(all).build();
    }

    public ApiResponse getOne(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role Not Found"));
        return ApiResponse.builder().message("Mana").success(true).data(role).build();
    }

    public ApiResponse edit(Long id, RoleDto roleDto) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role Not Found"));
        role.setName(roleDto.getName());
        role.setActive(roleDto.isActive());

        Role save = roleRepository.save(role);
        return ApiResponse.builder().message("Edited!").success(true).data(save).build();
    }

    public ApiResponse remove(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role Not Found"));
        roleRepository.delete(role);
        return ApiResponse.builder().message("Deleted").success(true).build();
    }
}
