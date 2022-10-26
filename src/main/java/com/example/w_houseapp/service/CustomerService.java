package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.CustomerDto;
import com.example.w_houseapp.entity.Customer;
import com.example.w_houseapp.repository.CustomerRepository;
import com.example.w_houseapp.solr.SolrCustomer;
import com.example.w_houseapp.solrRepository.SolrCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final SolrCustomerRepository solrCustomerRepository;

    public ApiResponse save(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setName(customerDto.getName());
        customer.setPhone(customerDto.getPhone());
        customer.setActive(customerDto.isActive());

        Customer save = customerRepository.save(customer);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Added!").success(true).data(save).build();
    }

    public ApiResponse gerAll() {
        List<Customer> all = customerRepository.findAll();
        return ApiResponse.builder().message("Mana").success(true).data(all).build();
    }

    public ApiResponse getOne(UUID uuid) {
        Customer customer = customerRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Customer Not Found"));
        return ApiResponse.builder().message("Mana").success(true).data(customer).build();
    }

    public ApiResponse edit(UUID uuid, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Customer Not Found"));

        customer.setName(customerDto.getName());
        customer.setPhone(customerDto.getPhone());
        customer.setActive(customerDto.isActive());

        Customer save = customerRepository.save(customer);
//        toSOLR(save.getId());
        return ApiResponse.builder().message("Edited").success(true).data(save).build();
    }

    public ApiResponse remove(UUID uuid) {
        Customer customer = customerRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Customer Not Found"));
        customerRepository.delete(customer);
        return ApiResponse.builder().message("Deleted").success(true).build();
    }

    private void toSOLR(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer Not Found"));
        Optional<SolrCustomer> optionalSolrCustomer = solrCustomerRepository.findById(id);
        SolrCustomer solrCustomer = new SolrCustomer();

        if (optionalSolrCustomer.isPresent()) {
            solrCustomer = optionalSolrCustomer.get();
        }
        solrCustomer.setId(customer.getId());
        solrCustomer.setName(customer.getName());
        solrCustomer.setPhone(customer.getPhone());
        solrCustomer.setActive(customer.isActive());

        solrCustomerRepository.save(solrCustomer);
    }
}
