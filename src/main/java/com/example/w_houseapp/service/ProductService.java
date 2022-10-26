package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.ProductDto;
import com.example.w_houseapp.entity.Category;
import com.example.w_houseapp.entity.Product;
import com.example.w_houseapp.repository.CategoryRepository;
import com.example.w_houseapp.repository.ProductRepository;
import com.example.w_houseapp.solrRepository.SolrProductRepository;
import com.example.w_houseapp.solr.SolrProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private
    final SolrProductRepository solrProductRepository;

    public ApiResponse save(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category Not Found"));

        Product product = new Product();

        product.setName(productDto.getName());
        product.setAmount(productDto.getAmount());
        product.setCategory(category);

        Product save = productRepository.save(product);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Added!").success(true).data(save).build(

        );
    }

    public ApiResponse getAll() {
//        List<SolrProduct> all = solrProductRepository.findAllByNameContainingIgnoreCase(productName);
        List<Product> all = productRepository.findAll();
        return ApiResponse.builder().message("Mana").success(true).data(all).build();

    }

    public ApiResponse getOne(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));

        return ApiResponse.builder().message("Mana").success(true).data(product).build();
    }

    public ApiResponse edit(Long id, ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category Not Found"));
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));

        product.setName(productDto.getName());
        product.setAmount(productDto.getAmount());
        product.setCategory(category);
        Product save = productRepository.save(product);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Edited!").success(true).data(save).build();

    }

    public ApiResponse remove(Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
        productRepository.delete(product);
        return ApiResponse.builder().message("Deleted!").success(true).build();

    }

    private void toSOLR(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));
        Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(() -> new RuntimeException("Category Not Found"));

        Optional <SolrProduct> optionalSolrProduct = solrProductRepository.findById(id);

        SolrProduct solrProduct = new SolrProduct();

        if (optionalSolrProduct.isPresent()){
            solrProduct = optionalSolrProduct.get();
        }
        solrProduct.setId(product.getId());
        solrProduct.setName(product.getName());
        solrProduct.setCategoryId(category.getId());
        solrProduct.setAmount(product.getAmount());

        solrProductRepository.save(solrProduct);
    }
}
