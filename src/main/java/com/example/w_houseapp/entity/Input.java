package com.example.w_houseapp.entity;

import com.example.w_houseapp.entity.enums.Currency;
import com.example.w_houseapp.entity.template.AbsEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Input extends AbsEntity {
    @ManyToOne
    private Supplier supplier;
    @CreatedDate
    private Date date;
    private Integer facture_number;
    @ManyToOne
    private Warehouse warehouse;
    @Enumerated
    private Currency currency;
}
