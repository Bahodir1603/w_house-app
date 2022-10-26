package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.OutputProductDto;
import com.example.w_houseapp.entity.Output;
import com.example.w_houseapp.entity.Output_Product;
import com.example.w_houseapp.entity.Product;
import com.example.w_houseapp.repository.OutputProductRepository;
import com.example.w_houseapp.repository.OutputRepository;
import com.example.w_houseapp.repository.ProductRepository;
import com.example.w_houseapp.solr.SolrOutputProduct;
import com.example.w_houseapp.solrRepository.SolrOutputProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutputProductService {
    private final OutputProductRepository outputProductRepository;
    private final OutputRepository outputRepository;
    private final ProductRepository productRepository;
    private final SolrOutputProductRepository solrOutputProductRepository;


    public ApiResponse save(OutputProductDto outputProductDto) {

        Output output = outputRepository.findById(UUID.fromString(outputProductDto.getOutputId())).orElseThrow(() -> new RuntimeException("Output Not Found"));
        Product product = productRepository.findById(outputProductDto.getProductId()).orElseThrow(() -> new RuntimeException("Product Not Found"));
        Output_Product output_product = new Output_Product();

        output_product.setOutput(output);
        output_product.setProduct(product);
        output_product.setAmount(outputProductDto.getAmount());
        output_product.setPrice(outputProductDto.getPrice());

        Output_Product save = outputProductRepository.save(output_product);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("ADDED").success(true).data(save).build();
    }

    public ApiResponse getAll() {
        List<Output_Product> all = outputProductRepository.findAll();
        return ApiResponse.builder().message("Mana").success(true).data(all).build();
    }

    public ApiResponse getOne(UUID uuid) {
        Output_Product output_product = outputProductRepository.findById(uuid).orElseThrow(() -> new RuntimeException("OutputProduct Not Found"));
        return ApiResponse.builder().message("Mana").success(true).data(output_product).build();
    }

    public ApiResponse edit(UUID uuid, OutputProductDto outputProductDto) {
        Output output = outputRepository.findById(UUID.fromString(outputProductDto.getOutputId())).orElseThrow(() -> new RuntimeException("Output Not Found"));
        Product product = productRepository.findById(outputProductDto.getProductId()).orElseThrow(() -> new RuntimeException("Product Not Found"));
        Output_Product output_product = outputProductRepository.findById(uuid).orElseThrow(() -> new RuntimeException("OutputProduct Not Found"));

        output_product.setOutput(output);
        output_product.setProduct(product);
        output_product.setAmount(outputProductDto.getAmount());
        output_product.setPrice(outputProductDto.getPrice());

        Output_Product save = outputProductRepository.save(output_product);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Edited").success(true).data(save).build();

    }

    public ApiResponse remove(UUID uuid) {
        Output_Product output_product = outputProductRepository.findById(uuid).orElseThrow(() -> new RuntimeException("OutputProduct Not Found"));
        outputProductRepository.delete(output_product);
        return ApiResponse.builder().message("Deleted").success(true).build();
    }

    private void toSOLR(UUID id) {
        Output_Product output_product = outputProductRepository.findById(id).orElseThrow(() -> new RuntimeException("OutputProduct Not Found"));
        Optional<SolrOutputProduct> optionalSolrOutputProduct = solrOutputProductRepository.findById(id);
        Output output = outputRepository.findById(output_product.getOutput().getId()).orElseThrow(() -> new RuntimeException("Output Not Found"));
        Product product = productRepository.findById(output_product.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product Not Found"));

        SolrOutputProduct solrOutputProduct = new SolrOutputProduct();
        if (optionalSolrOutputProduct.isPresent()) {
            solrOutputProduct = optionalSolrOutputProduct.get();
        }
        solrOutputProduct.setId(output_product.getId());
        solrOutputProduct.setOutputId(output.getId());
        solrOutputProduct.setProductId(product.getId());
        solrOutputProduct.setAmount(output_product.getAmount());
        solrOutputProduct.setPrice(output_product.getPrice());

        solrOutputProductRepository.save(solrOutputProduct);
    }
}
