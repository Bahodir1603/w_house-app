package com.example.w_houseapp.controller;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.RoleDto;
import com.example.w_houseapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody RoleDto roleDto){
        ApiResponse response = roleService.save(roleDto);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse response = roleService.getAll();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        ApiResponse response = roleService.getOne(id);
        return  ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody RoleDto roleDto){
        ApiResponse response = roleService.edit(id,roleDto);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse response = roleService.remove(id);
        return ResponseEntity.ok(response);
    }
}
