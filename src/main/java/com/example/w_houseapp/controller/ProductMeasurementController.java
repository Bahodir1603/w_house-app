package com.example.w_houseapp.controller;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.ProductMeasurementDto;
import com.example.w_houseapp.service.ProductMeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("productmeasurement")
@RequiredArgsConstructor
public class ProductMeasurementController {

    private final ProductMeasurementService productmeasurementService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ProductMeasurementDto productmeasurementDto) {
        ApiResponse response = productmeasurementService.save(productmeasurementDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        ApiResponse response = productmeasurementService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getOne(@PathVariable UUID uuid) {
        ApiResponse response = productmeasurementService.getOne(uuid);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid, @Valid @RequestBody ProductMeasurementDto productmeasurementDto) {
        ApiResponse response = productmeasurementService.edit(uuid, productmeasurementDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid){
        ApiResponse response = productmeasurementService.remove(uuid);
        return ResponseEntity.ok(response);
    }
}
