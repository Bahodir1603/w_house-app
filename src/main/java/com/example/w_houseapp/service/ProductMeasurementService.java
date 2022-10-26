package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.ProductMeasurementDto;
import com.example.w_houseapp.entity.Product;
import com.example.w_houseapp.entity.Product_measurement;
import com.example.w_houseapp.entity.enums.Measurement;
import com.example.w_houseapp.repository.ProductMeasurementRepository;
import com.example.w_houseapp.repository.ProductRepository;
import com.example.w_houseapp.solr.SolrProductMeasurement;
import com.example.w_houseapp.solrRepository.SolrProductMeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductMeasurementService {
    private final ProductMeasurementRepository produtmeasurementRepository;
    private final ProductRepository productRepository;
    private final SolrProductMeasurementRepository solrProductMeasurementRepository;

    public ApiResponse save(ProductMeasurementDto produtmeasurementDto) {
        Product product = productRepository.findById(produtmeasurementDto.getProductId()).orElseThrow(() -> new RuntimeException("Product Not Found"));
        Product_measurement produtmeasurement = new Product_measurement();

        produtmeasurement.setProduct(product);
        for (Measurement value : Measurement.values()) {
            if (value.toString().equals(produtmeasurementDto.getMeasurement())){
                produtmeasurement.setMeasurement(value);
            }
        }
        Product_measurement save = produtmeasurementRepository.save(produtmeasurement);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Added!").success(true).data(save).build();
    }

    public ApiResponse getAll() {
        List<Product_measurement> all = produtmeasurementRepository.findAll();
        return ApiResponse.builder().message("Mana").success(true).data(all).build();

    }

    public ApiResponse getOne(UUID uuid) {
        Product_measurement produtmeasurement = produtmeasurementRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Product_measurement Not Found"));

        return ApiResponse.builder().message("Mana").success(true).data(produtmeasurement).build();
    }

    public ApiResponse edit(UUID uuid, ProductMeasurementDto produtmeasurementDto) {
        Product product = productRepository.findById(produtmeasurementDto.getProductId()).orElseThrow(() -> new RuntimeException("Product Not Found"));
        Product_measurement produtmeasurement = produtmeasurementRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Product_measurement Not Found"));

        produtmeasurement.setProduct(product);
        for (Measurement value : Measurement.values()) {
            if (value.toString().equals(produtmeasurementDto.getMeasurement())){
                produtmeasurement.setMeasurement(value);
            }
        }
        Product_measurement save = produtmeasurementRepository.save(produtmeasurement);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Edited!").success(true).data(save).build();

    }
    public ApiResponse remove(UUID uuid) {

        Product_measurement produtmeasurement = produtmeasurementRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Product_measurement Not Found"));
        produtmeasurementRepository.delete(produtmeasurement);
        return ApiResponse.builder().message("Deleted!").success(true).build();

    }
    private void toSOLR(UUID id){

        Product_measurement product_measurement = produtmeasurementRepository.findById(id).orElseThrow(() -> new RuntimeException("ProductMeasurement Not Found"));
        Optional<SolrProductMeasurement> optionalSolrProductMeasurement = solrProductMeasurementRepository.findById(id);
        Product product = productRepository.findById(product_measurement.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product Not Found"));

        SolrProductMeasurement solrProductMeasurement = new SolrProductMeasurement();
        if (optionalSolrProductMeasurement.isPresent()){
            solrProductMeasurement = optionalSolrProductMeasurement.get();
        }
        solrProductMeasurement.setId(product_measurement.getId());
        solrProductMeasurement.setProductId(product.getId());
        solrProductMeasurement.setMeasurement(product_measurement.getMeasurement());

        solrProductMeasurementRepository.save(solrProductMeasurement);
    }
}
