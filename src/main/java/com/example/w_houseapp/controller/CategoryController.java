package com.example.w_houseapp.controller;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.CategoryDto;
import com.example.w_houseapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<?> add(@RequestBody CategoryDto categoryDto) {
        ApiResponse response = categoryService.save(categoryDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        ApiResponse response = categoryService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ApiResponse response = categoryService.getOne(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto) {
        ApiResponse response = categoryService.edit(id, categoryDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ApiResponse response = categoryService.remove(id);
        return ResponseEntity.ok(response);
    }
}
