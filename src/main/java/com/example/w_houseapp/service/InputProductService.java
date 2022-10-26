package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.InputProductDto;
import com.example.w_houseapp.entity.Input;
import com.example.w_houseapp.entity.Input_Product;
import com.example.w_houseapp.entity.Product;
import com.example.w_houseapp.repository.InputProductRepository;
import com.example.w_houseapp.repository.InputRepository;
import com.example.w_houseapp.repository.ProductRepository;
import com.example.w_houseapp.solr.SolrInputProduct;
import com.example.w_houseapp.solrRepository.SolrInputProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InputProductService {
    private final InputProductRepository inputProductRepository;
    private final InputRepository inputRepository;
    private final ProductRepository productRepository;
    private final SolrInputProductRepository solrInputProductRepositoryRepository;


    public ApiResponse save(InputProductDto inputProductDto) {

        Input input = inputRepository.findById(UUID.fromString(inputProductDto.getInputId())).orElseThrow(() -> new RuntimeException("Input Not Found"));
        Product product = productRepository.findById(inputProductDto.getProductId()).orElseThrow(() -> new RuntimeException("Product Not Found"));
        Input_Product input_product = new Input_Product();

        input_product.setInput(input);
        input_product.setProduct(product);
        input_product.setAmount(inputProductDto.getAmount());
        input_product.setPrice(inputProductDto.getPrice());

        Input_Product save = inputProductRepository.save(input_product);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("ADDED").success(true).data(save).build();
    }

    public ApiResponse getAll() {
        List<Input_Product> all = inputProductRepository.findAll();
        return ApiResponse.builder().message("Mana").success(true).data(all).build();
    }

    public ApiResponse getOne(UUID uuid) {
        Input_Product input_product = inputProductRepository.findById(uuid).orElseThrow(() -> new RuntimeException("InputProduct Not Found"));
        return ApiResponse.builder().message("Mana").success(true).data(input_product).build();
    }

    public ApiResponse edit(UUID uuid, InputProductDto inputProductDto) {
        Input input = inputRepository.findById(UUID.fromString(inputProductDto.getInputId())).orElseThrow(() -> new RuntimeException("Input Not Found"));
        Product product = productRepository.findById(inputProductDto.getProductId()).orElseThrow(() -> new RuntimeException("Product Not Found"));
        Input_Product input_product = inputProductRepository.findById(uuid).orElseThrow(() -> new RuntimeException("InputProduct Not Found"));

        input_product.setInput(input);
        input_product.setProduct(product);
        input_product.setAmount(inputProductDto.getAmount());
        input_product.setPrice(inputProductDto.getPrice());

        Input_Product save = inputProductRepository.save(input_product);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Edited").success(true).data(save).build();

    }

    public ApiResponse remove(UUID uuid) {
        Input_Product input_product = inputProductRepository.findById(uuid).orElseThrow(() -> new RuntimeException("InputProduct Not Found"));
        inputProductRepository.delete(input_product);
        return ApiResponse.builder().message("Deleted").success(true).build();
    }
    private void toSOLR(UUID id){
        Input_Product input_product = inputProductRepository.findById(id).orElseThrow(() -> new RuntimeException("InputProduct Not Found"));
        Optional<SolrInputProduct> optionalSolrInput = solrInputProductRepositoryRepository.findById(id);
        Input input = inputRepository.findById(input_product.getInput().getId()).orElseThrow(() -> new RuntimeException("Input Not Found"));
        Product product = productRepository.findById(input_product.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product Not Found"));

        SolrInputProduct solrInputProduct = new SolrInputProduct();

        if (optionalSolrInput.isPresent()){
            solrInputProduct = optionalSolrInput.get();
        }
        solrInputProduct.setId(input_product.getId());
        solrInputProduct.setInputId(input.getId());
        solrInputProduct.setProductId(product.getId());
        solrInputProduct.setAmount(input_product.getAmount());
        solrInputProduct.setPrice(input_product.getPrice());

        solrInputProductRepositoryRepository.save(solrInputProduct);
    }
}
