package com.example.w_houseapp.controller;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.SupplierDto;
import com.example.w_houseapp.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody SupplierDto supplierDto) {
        ApiResponse response = supplierService.save(supplierDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        ApiResponse response = supplierService.gerAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getOne(@PathVariable UUID uuid) {
        ApiResponse response = supplierService.getOne(uuid);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid, @RequestBody SupplierDto supplierDto) {
        ApiResponse response = supplierService.edit(uuid, supplierDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid){
        ApiResponse response = supplierService.remove(uuid);
        return ResponseEntity.ok(response);
    }
}
