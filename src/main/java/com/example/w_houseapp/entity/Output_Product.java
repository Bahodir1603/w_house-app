package com.example.w_houseapp.entity;

import com.example.w_houseapp.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Output_Product extends AbsEntity {
    @ManyToOne
    private Output output;
    @ManyToOne
    private Product product;
    private Double amount,price;
}
