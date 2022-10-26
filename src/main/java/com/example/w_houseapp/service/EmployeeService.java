package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.EmployeeDto;
import com.example.w_houseapp.entity.Employee;
import com.example.w_houseapp.entity.Warehouse;
import com.example.w_houseapp.repository.EmployeeRepository;
import com.example.w_houseapp.repository.WarehouseRepository;
import com.example.w_houseapp.solr.SolrEmployee;
import com.example.w_houseapp.solrRepository.SolrEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final WarehouseRepository warehouseRepository;
    private final SolrEmployeeRepository solrEmployeeRepository;
    public ApiResponse save(EmployeeDto employeeDto) {

        Warehouse warehouse = warehouseRepository.findById(employeeDto.getWarehouseId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));

        Employee employee = new Employee();

        employee.setFullName(employeeDto.getFullName());
        employee.setAge(employeeDto.getAge());
        employee.setPhone(employeeDto.getPhone());
        employee.setSalary(employeeDto.getSalary());
        employee.setWarehouse(warehouse);


        Employee save = employeeRepository.save(employee);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Added").success(true).data(save).build();

    }

    public ApiResponse getAll() {
        List<Employee> all = employeeRepository.findAll();
        return ApiResponse.builder().message("Mana").success(true).data(all).build();

    }

    public ApiResponse getOne(UUID uuid) {
        Employee employee = employeeRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));
        return ApiResponse.builder().message("Mana").success(true).data(employee).build();
    }

    public ApiResponse edit(UUID uuid,EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));
        Warehouse warehouse = warehouseRepository.findById(employeeDto.getWarehouseId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));

        employee.setFullName(employeeDto.getFullName());
        employee.setAge(employeeDto.getAge());
        employee.setPhone(employeeDto.getPhone());
        employee.setSalary(employeeDto.getSalary());
        employee.setWarehouse(warehouse);

        Employee save = employeeRepository.save(employee);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Edited").success(true).data(save).build();


    }

    public ApiResponse remove(UUID uuid) {
        Employee employee = employeeRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Employee Not Found"));
        employeeRepository.delete(employee);
        return ApiResponse.builder().message("Deleted").success(true).build();

    }
    private void toSOLR(UUID id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found"));
        Warehouse warehouse = warehouseRepository.findById(employee.getWarehouse().getId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));
        Optional<SolrEmployee> optionalSolrEmployee = solrEmployeeRepository.findById(id);
        SolrEmployee solrEmployee = new SolrEmployee();

        if (optionalSolrEmployee.isPresent()){
            solrEmployee = optionalSolrEmployee.get();
        }
        solrEmployee.setId(employee.getId());
        solrEmployee.setFullName(employee.getFullName());
        solrEmployee.setAge(employee.getAge());
        solrEmployee.setSalary(employee.getSalary());
        solrEmployee.setPhone(employee.getPhone());
        solrEmployee.setWarehouseId(warehouse.getId());

    }
}
