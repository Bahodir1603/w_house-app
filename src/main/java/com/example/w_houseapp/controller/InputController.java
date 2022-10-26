package com.example.w_houseapp.controller;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.InputDto;
import com.example.w_houseapp.service.InputService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("input")
@RequiredArgsConstructor
public class InputController {
    private final InputService inputService;
    @PostMapping
    public ResponseEntity<?> add(@RequestBody InputDto inputDto){
        ApiResponse response = inputService.save(inputDto);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse response= inputService.getAll();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<?> getOne(@PathVariable UUID uuid){
        ApiResponse response = inputService.getOne(uuid);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid,@RequestBody InputDto inputDto){
        ApiResponse response = inputService.edit(uuid,inputDto);
        return ResponseEntity.status(response.isSuccess()?200:404).body(response);
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid){
        ApiResponse response = inputService.remove(uuid);
        return ResponseEntity.ok(response);
    }
}
