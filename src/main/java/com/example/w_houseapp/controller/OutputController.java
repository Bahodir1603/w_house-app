package com.example.w_houseapp.controller;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.OutputDto;
import com.example.w_houseapp.service.OutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("output")
@RequiredArgsConstructor
public class OutputController {
    private final OutputService outputService;
    @PostMapping
    public ResponseEntity<?> add(@RequestBody OutputDto outputDto){
        ApiResponse response = outputService.save(outputDto);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse response= outputService.getAll();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<?> getOne(@PathVariable UUID uuid){
        ApiResponse response = outputService.getOne(uuid);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid,@RequestBody OutputDto outputDto){
        ApiResponse response = outputService.edit(uuid,outputDto);
        return ResponseEntity.status(response.isSuccess()?200:404).body(response);
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid){
        ApiResponse response = outputService.remove(uuid);
        return ResponseEntity.ok(response);
    }
}
