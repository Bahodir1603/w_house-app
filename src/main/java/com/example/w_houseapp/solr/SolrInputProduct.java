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
public class SolrInputProduct {
    @Field("id")
    UUID id;
    @Field("inputId")
    UUID inputId;
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
//    @Field("inputId")
//    public void setInput(UUID inputId) {
//        this.inputId = inputId;
//    }
//
//    @Field("productId")
//    public void setProduct(Long productId) {
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
