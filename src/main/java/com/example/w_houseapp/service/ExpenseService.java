package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.ExpenseDto;
import com.example.w_houseapp.entity.Supplier;
import com.example.w_houseapp.entity.Expense;
import com.example.w_houseapp.entity.Warehouse;
import com.example.w_houseapp.entity.enums.ExpenseType;
import com.example.w_houseapp.entity.enums.PayType;
import com.example.w_houseapp.repository.SupplierRepository;
import com.example.w_houseapp.repository.ExpenseRepository;
import com.example.w_houseapp.repository.WarehouseRepository;
import com.example.w_houseapp.solr.SolrExpense;
import com.example.w_houseapp.solrRepository.SolrExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final SupplierRepository supplierRepository;

    private final WarehouseRepository warehouseRepository;
    private final SolrExpenseRepository solrExpenseRepository;

    public ApiResponse save(ExpenseDto expenseDto) {

        Supplier supplier = supplierRepository.findById(UUID.fromString(expenseDto.getSupplierId())).orElseThrow(() -> new RuntimeException("Supplier Not Found"));
        Warehouse warehouse = warehouseRepository.findById(expenseDto.getWarehouseId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));

        Expense expense = new Expense();

        expense.setSupplier(supplier);
        expense.setWarehouse(warehouse);
        for (ExpenseType value : ExpenseType.values()) {
            if (value.toString().equals(expenseDto.getExpenseType())){
                expense.setExpenseType(value);
            }
        }
        expense.setAmount(expenseDto.getAmount());

        for (PayType value : PayType.values()) {
            if (value.toString().equals(expenseDto.getPayType())){
                expense.setPayType(value);
            }
        }

        Expense save = expenseRepository.save(expense);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Added!").success(true).data(save).build(

        );
    }

    public ApiResponse getAll() {
        List<Expense> all = expenseRepository.findAll();
        return ApiResponse.builder().message("Mana").success(true).data(all).build();

    }

    public ApiResponse getOne(UUID uuid) {
        Expense expense = expenseRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Expense Not Found"));

        return ApiResponse.builder().message("Mana").success(true).data(expense).build();
    }

    public ApiResponse edit(UUID uuid, ExpenseDto expenseDto) {

        Expense expense = expenseRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Expense Not Found"));
        Supplier supplier = supplierRepository.findById(UUID.fromString(expenseDto.getSupplierId())).orElseThrow(() -> new RuntimeException("Supplier Not Found"));
        Warehouse warehouse = warehouseRepository.findById(expenseDto.getWarehouseId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));

        expense.setSupplier(supplier);
        expense.setWarehouse(warehouse);
        expense.setAmount(expenseDto.getAmount());

        for (PayType value : PayType.values()) {
            if (value.toString().equals(expenseDto.getPayType())){
                expense.setPayType(value);
            }
        }

        Expense save = expenseRepository.save(expense);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Edited!").success(true).data(save).build();

    }

    public ApiResponse remove(UUID uuid) {

        Expense expense = expenseRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Expense Not Found"));
        expenseRepository.delete(expense);
        return ApiResponse.builder().message("Deleted!").success(true).build();

    }
    private void toSOLR(UUID id){
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense Not Found"));
        Optional<SolrExpense> optionalSolrExpense = solrExpenseRepository.findById(id);
        Supplier supplier = supplierRepository.findById(expense.getSupplier().getId()).orElseThrow(() -> new RuntimeException("Supplier Not Found"));
        Warehouse warehouse = warehouseRepository.findById(expense.getWarehouse().getId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));

        SolrExpense solrExpense = new SolrExpense();
        if (optionalSolrExpense.isPresent()){
            solrExpense = optionalSolrExpense.get();
        }
        solrExpense.setId(expense.getId());
        solrExpense.setDescription(expense.getDescription());
        solrExpense.setAmount(expense.getAmount());
        solrExpense.setSupplierId(supplier.getId());
        solrExpense.setWarehouseId(warehouse.getId());
        solrExpense.setPayType(expense.getPayType());

        solrExpenseRepository.save(solrExpense);
    }
}
