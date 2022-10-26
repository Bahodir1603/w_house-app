package com.example.w_houseapp.entity;

import com.example.w_houseapp.entity.template.AbsNameEntity;
import lombok.*;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity

public class Product extends AbsNameEntity {
    @ManyToOne
    private Category category;
    private Double amount;

}
