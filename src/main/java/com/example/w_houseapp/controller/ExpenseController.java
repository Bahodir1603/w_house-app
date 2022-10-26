package com.example.w_houseapp.controller;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.ExpenseDto;
import com.example.w_houseapp.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ExpenseDto expenseDto) {
        ApiResponse response = expenseService.save(expenseDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        ApiResponse response = expenseService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getOne(@PathVariable UUID uuid) {
        ApiResponse response = expenseService.getOne(uuid);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{UUid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid, @Valid @RequestBody ExpenseDto expenseDto) {
        ApiResponse response = expenseService.edit(uuid, expenseDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid){
        ApiResponse response = expenseService.remove(uuid);
        return ResponseEntity.ok(response);
    }
}
