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
public class Employee extends AbsEntity {
    private String fullName;
    private Integer age;
    private Double salary;
    private String phone;
    @ManyToOne
    private Warehouse warehouse;
}
