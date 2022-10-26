package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.SupplierDto;
import com.example.w_houseapp.entity.Supplier;
import com.example.w_houseapp.repository.SupplierRepository;
import com.example.w_houseapp.solr.SolrSupplier;
import com.example.w_houseapp.solrRepository.SolrSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SolrSupplierRepository solrSupplierRepository;

    public ApiResponse save(SupplierDto supplierDto) {
        Supplier supplier = new Supplier();

        supplier.setName(supplierDto.getName());
        supplier.setPhone(supplierDto.getPhone());
        supplier.setActive(supplierDto.isActive());

        Supplier save = supplierRepository.save(supplier);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Added!").success(true).data(save).build();
    }

    public ApiResponse gerAll() {
        List<Supplier> all = supplierRepository.findAll();
        return ApiResponse.builder().message("Mana").success(true).data(all).build();
    }

    public ApiResponse getOne(UUID uuid) {
        Supplier supplier = supplierRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Supplier Not Found"));
        return ApiResponse.builder().message("Mana").success(true).data(supplier).build();
    }

    public ApiResponse edit(UUID uuid, SupplierDto supplierDto) {
        Supplier supplier = supplierRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Supplier Not Found"));

        supplier.setName(supplierDto.getName());
        supplier.setPhone(supplierDto.getPhone());
        supplier.setActive(supplierDto.isActive());

        Supplier save = supplierRepository.save(supplier);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Edited").success(true).data(save).build();
    }

    public ApiResponse remove(UUID uuid) {
        Supplier supplier = supplierRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Supplier Not Found"));
        supplierRepository.delete(supplier);
        return ApiResponse.builder().message("Deleted").success(true).build();
    }

    private void toSOLR(UUID id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier Not Found"));
        Optional<SolrSupplier> optionalSolrSupplier = solrSupplierRepository.findById(id);

        SolrSupplier solrSupplier = new SolrSupplier();
        if (optionalSolrSupplier.isPresent()) {
            solrSupplier = optionalSolrSupplier.get();
        }
        solrSupplier.setId(supplier.getId());
        solrSupplier.setName(supplier.getName());
        solrSupplier.setPhone(supplier.getPhone());
        solrSupplier.setActive(supplier.isActive());

        solrSupplierRepository.save(solrSupplier);
    }

}
