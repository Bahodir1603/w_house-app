package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.OutputDto;
import com.example.w_houseapp.entity.Output;
import com.example.w_houseapp.entity.Customer;
import com.example.w_houseapp.entity.Warehouse;
import com.example.w_houseapp.entity.enums.Currency;
import com.example.w_houseapp.repository.OutputRepository;
import com.example.w_houseapp.repository.CustomerRepository;
import com.example.w_houseapp.repository.WarehouseRepository;
import com.example.w_houseapp.solr.SolrOutput;
import com.example.w_houseapp.solrRepository.SolrOutputRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutputService {
    private final OutputRepository outputRepository;
    private final CustomerRepository customerRepository;
    private final WarehouseRepository warehouseRepository;
    private final SolrOutputRepository solrOutputRepository;

    public ApiResponse save(OutputDto outputDto) {

        Customer customer = customerRepository.findById(UUID.fromString(outputDto.getCustomerId())).orElseThrow(() -> new RuntimeException("Customer Not Found"));
        Warehouse warehouse = warehouseRepository.findById(outputDto.getWarehouseId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));

        Output output = new Output();
        output.setCustomer(customer);
        output.setWarehouse(warehouse);

        for (Currency value : Currency.values()) {
            if (value.toString().equals(outputDto.getCurrency())) {
                output.setCurrency(value);
            }
        }
        Output save = outputRepository.save(output);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Added").success(true).data(save).build();
    }

    public ApiResponse getAll() {
        List<Output> all = outputRepository.findAll();
        return ApiResponse.builder().message("Mana!").success(true).data(all).build();
    }

    public ApiResponse getOne(UUID uuid) {
        Output output = outputRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Output Not Found"));
        return ApiResponse.builder().message("Mana!").success(true).data(output).build();
    }

    public ApiResponse edit(UUID uuid, OutputDto outputDto) {

        Customer customer = customerRepository.findById(UUID.fromString(outputDto.getCustomerId())).orElseThrow(() -> new RuntimeException("Customer Not Found"));
        Warehouse warehouse = warehouseRepository.findById(outputDto.getWarehouseId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));
        Output output = outputRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Output Not Found"));

        output.setCustomer(customer);
        output.setWarehouse(warehouse);

        for (Currency value : Currency.values()) {
            if (value.toString().equals(outputDto.getCurrency())) {
                output.setCurrency(value);
            }
        }
        Output save = outputRepository.save(output);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Edited!").success(true).data(save).build();

    }

    public ApiResponse remove(UUID uuid) {
        Output output = outputRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Output Not Found"));
        outputRepository.delete(output);
        return ApiResponse.builder().message("Deleted!").success(true).build();
    }

    private void toSOLR(UUID id) {

        Output output = outputRepository.findById(id).orElseThrow(() -> new RuntimeException("Output Not Found"));
        Optional<SolrOutput> optionalSolrOutput = solrOutputRepository.findById(id);
        Customer customer = customerRepository.findById(output.getCustomer().getId()).orElseThrow(() -> new RuntimeException("Customer Not Found"));
        Warehouse warehouse = warehouseRepository.findById(output.getWarehouse().getId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));

        SolrOutput solrOutput = new SolrOutput();
        if (optionalSolrOutput.isPresent()) {
            solrOutput = optionalSolrOutput.get();
        }
        solrOutput.setId(output.getId());
        solrOutput.setCustomerId(customer.getId());
        solrOutput.setDate(output.getDate());
        solrOutput.setWarehouseId(warehouse.getId());
        solrOutput.setCurrency(output.getCurrency());

        solrOutputRepository.save(solrOutput);
    }
}
