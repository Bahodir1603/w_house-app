package com.example.w_houseapp.controller;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.InputProductDto;
import com.example.w_houseapp.service.InputProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("inputproduct")
@RequiredArgsConstructor
public class InputProductController {
    private final InputProductService inputProductService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody InputProductDto inputProductDto) {
        ApiResponse response = inputProductService.save(inputProductDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        ApiResponse response = inputProductService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getOne(@PathVariable UUID uuid) {
        ApiResponse response = inputProductService.getOne(uuid);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid, @RequestBody InputProductDto inputProductDto) {
        ApiResponse response = inputProductService.edit(uuid, inputProductDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid) {
        ApiResponse response = inputProductService.remove(uuid);
        return ResponseEntity.ok(response);
    }
}
