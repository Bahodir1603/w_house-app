package com.example.w_houseapp.solr;

import com.example.w_houseapp.entity.Product;
import com.example.w_houseapp.entity.enums.Measurement;
import com.example.w_houseapp.entity.template.AbsEntity;
import lombok.*;
import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SolrProductMeasurement {
    @Field("id")
    UUID id;
    @Field("productId")
    Long productId;
    @Field("measurement")
    Measurement measurement;

//    @Field("id")
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    @Field("productId")
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }
//
//    @Field("measurement")
//    public void setMeasurement(Measurement measurement) {
//        this.measurement = measurement;
//    }
}
