package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.PaymentDto;
import com.example.w_houseapp.entity.Customer;
import com.example.w_houseapp.entity.Payment;
import com.example.w_houseapp.entity.Warehouse;
import com.example.w_houseapp.entity.enums.PayType;
import com.example.w_houseapp.repository.CustomerRepository;
import com.example.w_houseapp.repository.PaymentRepository;
import com.example.w_houseapp.repository.WarehouseRepository;
import com.example.w_houseapp.solr.SolrPayment;
import com.example.w_houseapp.solrRepository.SolrPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;

    private final WarehouseRepository warehouseRepository;
    private final SolrPaymentRepository solrPaymentRepository;

    public ApiResponse save(PaymentDto paymentDto) {

        Customer customer = customerRepository.findById(UUID.fromString(paymentDto.getCustomerId())).orElseThrow(() -> new RuntimeException("Customer Not Found"));
        Warehouse warehouse = warehouseRepository.findById(paymentDto.getWarehouseId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));

        Payment payment = new Payment();

        payment.setCustomer(customer);
        payment.setWarehouse(warehouse);
        payment.setAmount(paymentDto.getAmount());

        for (PayType value : PayType.values()) {
            if (value.toString().equals(paymentDto.getPayType())){
                payment.setPayType(value);
            }
        }

        Payment save = paymentRepository.save(payment);
//        toSolr(save.getId());
        return ApiResponse.builder().message("Added!").success(true).data(save).build(

        );
    }

    public ApiResponse getAll() {
        List<Payment> all = paymentRepository.findAll();
        return ApiResponse.builder().message("Mana").success(true).data(all).build();

    }

    public ApiResponse getOne(UUID uuid) {
        Payment payment = paymentRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Payment Not Found"));

        return ApiResponse.builder().message("Mana").success(true).data(payment).build();
    }

    public ApiResponse edit(UUID uuid, PaymentDto paymentDto) {

        Payment payment = paymentRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Payment Not Found"));
        Customer customer = customerRepository.findById(UUID.fromString(paymentDto.getCustomerId())).orElseThrow(() -> new RuntimeException("Customer Not Found"));
        Warehouse warehouse = warehouseRepository.findById(paymentDto.getWarehouseId()).orElseThrow(() -> new RuntimeException("Warehousre Not Found"));

        payment.setCustomer(customer);
        payment.setWarehouse(warehouse);
        payment.setAmount(paymentDto.getAmount());

        for (PayType value : PayType.values()) {
            if (value.toString().equals(paymentDto.getPayType())){
                payment.setPayType(value);
            }
        }

        Payment save = paymentRepository.save(payment);
//        toSolr(save.getId());
        return ApiResponse.builder().message("Edited!").success(true).data(save).build();

    }

    public ApiResponse remove(UUID uuid) {

        Payment payment = paymentRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Payment Not Found"));
        paymentRepository.delete(payment);
        return ApiResponse.builder().message("Edited!").success(true).build();

    }
    private void  toSolr(UUID id){
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment Not Found"));
        Optional<SolrPayment> optionalSolrPayment = solrPaymentRepository.findById(id);
        Customer customer = customerRepository.findById(payment.getCustomer().getId()).orElseThrow(() -> new RuntimeException("Customer Not Found"));
        Warehouse warehouse = warehouseRepository.findById(payment.getWarehouse().getId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));

        SolrPayment solrPayment = new SolrPayment();
        if (optionalSolrPayment.isPresent()){
            solrPayment = optionalSolrPayment.get();
        }
        solrPayment.setId(payment.getId());
        solrPayment.setCustomerId(customer.getId());
        solrPayment.setAmount(payment.getAmount());
        solrPayment.setWarehouseId(warehouse.getId());
        solrPayment.setPayType(payment.getPayType());

        solrPaymentRepository.save(solrPayment);
    }
}
