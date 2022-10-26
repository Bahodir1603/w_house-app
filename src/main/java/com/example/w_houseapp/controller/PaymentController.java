package com.example.w_houseapp.controller;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.PaymentDto;
import com.example.w_houseapp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody PaymentDto paymentDto) {
        ApiResponse response = paymentService.save(paymentDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        ApiResponse response = paymentService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getOne(@PathVariable UUID uuid) {
        ApiResponse response = paymentService.getOne(uuid);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{UUid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid, @Valid @RequestBody PaymentDto paymentDto) {
        ApiResponse response = paymentService.edit(uuid, paymentDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid){
        ApiResponse response = paymentService.remove(uuid);
        return ResponseEntity.ok(response);
    }
}
