package com.example.w_houseapp.entity;

import com.example.w_houseapp.entity.enums.ExpenseType;
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
public class Expense extends AbsEntity {

    private String description;
    private Double amount;
    @ManyToOne
    private Supplier supplier;
    @Enumerated
    private ExpenseType expenseType;
    @ManyToOne
    private Warehouse warehouse;
    @Enumerated
    private PayType payType;
}
