package com.example.w_houseapp.controller;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.OutputProductDto;
import com.example.w_houseapp.service.OutputProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("outputproduct")
@RequiredArgsConstructor
public class OutputProductController {
    private final OutputProductService outputProductService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody OutputProductDto outputProductDto) {
        ApiResponse response = outputProductService.save(outputProductDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        ApiResponse response = outputProductService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getOne(@PathVariable UUID uuid) {
        ApiResponse response = outputProductService.getOne(uuid);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid, @RequestBody OutputProductDto outputProductDto) {
        ApiResponse response = outputProductService.edit(uuid, outputProductDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid) {
        ApiResponse response = outputProductService.remove(uuid);
        return ResponseEntity.ok(response);
    }
}
