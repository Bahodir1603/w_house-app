package com.example.w_houseapp.solr;

import lombok.*;
import org.apache.solr.client.solrj.beans.Field;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SolrProduct {

    @Field("id")
    Long id;
    @Field("name")
    String name;
    @Field("categoryId")
    Long categoryId;
    @Field("amount")
    Double amount;

//    @Field("id")
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Field("name")
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Field("categoryId")
//    public void setCategoryId(Long categoryId){this.categoryId=categoryId;}
//
//    @Field("amount")
//    public void setAmount(Double amount) {
//        this.amount = amount;
//    }

}
