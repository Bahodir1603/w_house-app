package com.example.w_houseapp.controller;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.WarehouseDto;
import com.example.w_houseapp.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody WarehouseDto warehouseDto) {
        ApiResponse response = warehouseService.save(warehouseDto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll() {
        ApiResponse response = warehouseService.getAll();
        return ResponseEntity.ok(response);

//    @GetMapping
//    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "") String search) {
//        ApiResponse response = warehouseService.getAll(page, size, search);
//        return ResponseEntity.ok(response);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ApiResponse response = warehouseService.getOne(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResponse response = warehouseService.remove(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }


    @PreAuthorize(value = "hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody WarehouseDto warehouseDto) {
        ApiResponse response = warehouseService.edit(id, warehouseDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
}
