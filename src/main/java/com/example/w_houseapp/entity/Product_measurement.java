package com.example.w_houseapp.entity;

import com.example.w_houseapp.entity.enums.Measurement;
import com.example.w_houseapp.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Product_measurement extends AbsEntity {
    @ManyToOne
    private Product product;
    @Enumerated
    private Measurement measurement;
}
