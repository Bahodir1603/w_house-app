package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.CategoryDto;
import com.example.w_houseapp.entity.Category;
import com.example.w_houseapp.repository.CategoryRepository;
import com.example.w_houseapp.solr.SolrCategory;
import com.example.w_houseapp.solrRepository.SolrCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final SolrCategoryRepository solrCategoryRepository;

    public ApiResponse save(CategoryDto categoryDto) {
        Category category = new Category();

        category.setName(categoryDto.getName());
        Category save = categoryRepository.save(category);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Added!").success(true).data(save).build(

        );
    }

    public ApiResponse getAll() {
        List<Category> all = categoryRepository.findAll();
        return ApiResponse.builder().message("Mana").success(true).data(all).build();

    }

    public ApiResponse getOne(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found"));

        return ApiResponse.builder().message("Mana").success(true).data(category).build();
    }

    public ApiResponse edit(Long id, CategoryDto categoryDto) {

        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found"));

        category.setName(categoryDto.getName());
        Category save = categoryRepository.save(category);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Edited!").success(true).data(save).build();

    }
    public ApiResponse remove(Long id) {

        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found"));
        categoryRepository.delete(category);
        return ApiResponse.builder().message("Edited!").success(true).build();

    }
    private void toSOLR(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found"));
        Optional<SolrCategory> optionalSolrCategory = solrCategoryRepository.findById(id);
        SolrCategory solrCategory = new SolrCategory();
        if (optionalSolrCategory.isPresent()){
            solrCategory = optionalSolrCategory.get();
        }
        solrCategory.setId(category.getId());
        solrCategory.setName(category.getName());

        solrCategoryRepository.save(solrCategory);
    }
}
