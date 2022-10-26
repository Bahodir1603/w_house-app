package com.example.w_houseapp.controller;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.EmployeeDto;
import com.example.w_houseapp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody EmployeeDto employeeDto){
       ApiResponse response = employeeService.save(employeeDto);
       return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        ApiResponse response = employeeService.getAll();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<?> getOne(@PathVariable UUID uuid){
        ApiResponse response = employeeService.getOne(uuid);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{uuid}")
    public ResponseEntity<?> update(@PathVariable UUID uuid,@Valid @RequestBody EmployeeDto employeeDto){
        ApiResponse response = employeeService.edit(uuid,employeeDto );
        return ResponseEntity.status(response.isSuccess()? 200 : 400).body(response);
    }
    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable UUID uuid){
        ApiResponse response = employeeService.remove(uuid);
        return ResponseEntity.ok(response);
    }


}
