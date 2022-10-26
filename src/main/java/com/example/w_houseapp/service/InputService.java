package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.InputDto;
import com.example.w_houseapp.entity.Input;
import com.example.w_houseapp.entity.Supplier;
import com.example.w_houseapp.entity.Warehouse;
import com.example.w_houseapp.entity.enums.Currency;
import com.example.w_houseapp.repository.InputRepository;
import com.example.w_houseapp.repository.SupplierRepository;
import com.example.w_houseapp.repository.WarehouseRepository;
import com.example.w_houseapp.solr.SolrInput;
import com.example.w_houseapp.solrRepository.SolrInputRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InputService {
    private final InputRepository inputRepository;
    private final SupplierRepository supplierRepository;
    private final WarehouseRepository warehouseRepository;
    private final SolrInputRepository solrInputRepository;

    public ApiResponse save(InputDto inputDto) {

        Supplier supplier = supplierRepository.findById(UUID.fromString(inputDto.getSupplierId())).orElseThrow(() -> new RuntimeException("Supplier Not Found"));
        Warehouse warehouse = warehouseRepository.findById(inputDto.getWarehouseId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));

        Input input = new Input();
        input.setSupplier(supplier);
        input.setFacture_number(input.getFacture_number());
        input.setWarehouse(warehouse);

        for (Currency value : Currency.values()) {
            if (value.toString().equals(inputDto.getCurrency())) {
                input.setCurrency(value);
            }
        }
        Input save = inputRepository.save(input);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Added").success(true).data(save).build();
    }

    public ApiResponse getAll() {
        List<Input> all = inputRepository.findAll();
        return ApiResponse.builder().message("Mana!").success(true).data(all).build();
    }

    public ApiResponse getOne(UUID uuid) {
        Input input = inputRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Input Not Found"));
        return ApiResponse.builder().message("Mana!").success(true).data(input).build();
    }

    public ApiResponse edit(UUID uuid, InputDto inputDto) {

        Supplier supplier = supplierRepository.findById(UUID.fromString(inputDto.getSupplierId())).orElseThrow(() -> new RuntimeException("Supplier Not Found"));
        Warehouse warehouse = warehouseRepository.findById(inputDto.getWarehouseId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));
        Input input = inputRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Input Not Found"));

        input.setSupplier(supplier);
        input.setFacture_number(inputDto.getFacture_number());
        input.setWarehouse(warehouse);

        for (Currency value : Currency.values()) {
            if (value.toString().equals(inputDto.getCurrency())){
                input.setCurrency(value);
            }
        }
        Input save = inputRepository.save(input);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Edited!").success(true).data(save).build();

    }

    public ApiResponse remove(UUID uuid) {
        Input input = inputRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Input Not Found"));
        inputRepository.delete(input);
        return  ApiResponse.builder().message("Deleted!").success(true).build();
    }
    private void toSOLR(UUID id){
        Input input = inputRepository.findById(id).orElseThrow(() -> new RuntimeException("Input Not Found"));
        Optional<SolrInput> optionalSolrInput = solrInputRepository.findById(id);
        Supplier supplier = supplierRepository.findById(input.getSupplier().getId()).orElseThrow(() -> new RuntimeException("Supplier Not Found"));
        Warehouse warehouse = warehouseRepository.findById(input.getWarehouse().getId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));

        SolrInput solrInput = new SolrInput();
        if (optionalSolrInput.isPresent()){
            solrInput  = optionalSolrInput.get();
        }
        solrInput.setId(input.getId());
        solrInput.setSupplierId(supplier.getId());
        solrInput.setDate(input.getDate());
        solrInput.setFacture_number(input.getFacture_number());
        solrInput.setWarehouseId(warehouse.getId());
        solrInput.setCurrency(input.getCurrency());

        solrInputRepository.save(solrInput);
    }
}
