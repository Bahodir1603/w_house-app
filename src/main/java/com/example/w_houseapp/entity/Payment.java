package com.example.w_houseapp.entity;

import com.example.w_houseapp.entity.enums.PayType;
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
public class Payment extends AbsEntity {

    @ManyToOne
    private Customer customer;
    private Double amount;
    @ManyToOne
    private Warehouse warehouse;
    @Enumerated
    private PayType payType;

}
