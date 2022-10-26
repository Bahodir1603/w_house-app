package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.WarehouseDto;
import com.example.w_houseapp.entity.Warehouse;
import com.example.w_houseapp.solrRepository.SolrWarehouseRepository;
import com.example.w_houseapp.repository.WarehouseRepository;
import com.example.w_houseapp.solr.SolrWarehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final SolrWarehouseRepository solrWarehouseRepository;

    public ApiResponse save(WarehouseDto warehouseDto) {

        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDto.getName());
        warehouse.setActive(warehouseDto.isActive());

        Warehouse save = warehouseRepository.save(warehouse);
        toSOLR(save.getId());
        return ApiResponse.builder().message("Added").success(true).data(save).build();


    }

    public ApiResponse getAll() {
        Iterable<SolrWarehouse> all = solrWarehouseRepository.findAll();
        return ApiResponse.builder().message("Mana").success(true).data(all).build();
    }


//    public ApiResponse getAll(int page,int size,String search) {
//        Pageable pageable = PageRequest.of(page,size);
//        Page<Warehouse> data = null;
//
//        if (search.equals("")){
//           data =  warehouseRepository.findAll(pageable);
//        }else {
//            data = warehouseRepository.findAllByNameContainingIgnoreCase(search, pageable);
//        }
//        return ApiResponse.builder().message("Mana").success(true).data(data).build();
//    }

    public ApiResponse getOne(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));

        return ApiResponse.builder().message("Mana").success(true).data(warehouse).build();
    }

    public ApiResponse edit(Long id, WarehouseDto warehouseDto) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));
        warehouse.setName(warehouseDto.getName());
        warehouse.setActive(warehouseDto.isActive());

        Warehouse save = warehouseRepository.save(warehouse);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Edited!").success(true).data(save).build();

    }

    public ApiResponse remove(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));
        warehouseRepository.delete(warehouse);
        SolrWarehouse solrWarehouse = solrWarehouseRepository.findById(id).orElseThrow(() -> new RuntimeException("SolrWarehouse Not Found"));
        solrWarehouseRepository.delete(solrWarehouse);
        return ApiResponse.builder().message("Deleted!").success(true).build();

    }

    private void toSOLR(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));

        Optional<SolrWarehouse> optionalSolrWarehouse = solrWarehouseRepository.findById(id);

        SolrWarehouse solrWarehouse = new SolrWarehouse();
        if (optionalSolrWarehouse.isPresent()) {
            solrWarehouse = optionalSolrWarehouse.get();
        }
        solrWarehouse.setId(warehouse.getId());
        solrWarehouse.setName(warehouse.getName());
        solrWarehouse.setActive(warehouse.isActive());

        solrWarehouseRepository.save(solrWarehouse);

    }
}
