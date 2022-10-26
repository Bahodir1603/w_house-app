package com.example.w_houseapp.solr;

import lombok.*;
import org.apache.solr.client.solrj.beans.Field;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SolrOutputProduct {
    @Field("id")
    UUID id;
    @Field("outputId")
    UUID outputId;
    @Field("productId")
    Long productId;
    @Field("amount")
    Double amount;
    @Field("price")
    Double price;

//    @Field("id")
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    @Field("outputId")
//    public void setOutputId(UUID outputId) {
//        this.outputId = outputId;
//    }
//
//    @Field("productId")
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }
//
//    @Field("amount")
//    public void setAmount(Double amount) {
//        this.amount = amount;
//    }
//
//    @Field("price")
//    public void setPrice(Double price) {
//        this.price = price;
//    }
}
