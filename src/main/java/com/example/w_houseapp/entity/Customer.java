package com.example.w_houseapp.entity;

import com.example.w_houseapp.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Customer extends AbsEntity {

    private String name,phone;
    private boolean active;

}
