package com.example.w_houseapp.controller;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.UserDto;
import com.example.w_houseapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody UserDto userDto){
        ApiResponse response = userService.save(userDto);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse response = userService.getAll();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<?> getOne(@PathVariable UUID uuid){
        ApiResponse response = userService.getOne(uuid);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid, @RequestBody UserDto userDto){
        ApiResponse response = userService.edit(uuid,userDto);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid){
        ApiResponse response = userService.remove(uuid);
        return ResponseEntity.ok(response);
    }
}
