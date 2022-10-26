package com.example.w_houseapp.solr;

import com.example.w_houseapp.entity.enums.PayType;
import lombok.*;
import org.apache.solr.client.solrj.beans.Field;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SolrPayment {
    @Field("id")
    UUID id;
    @Field("customerId")
    UUID customerId;
    @Field("amount")
    Double amount;
    @Field("warehouseId")
    Long warehouseId;
    @Field("payType")
    PayType payType;

//    @Field("id")
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    @Field("customerId")
//    public void setCustomerId(UUID customerId) {
//        this.customerId = customerId;
//    }
//
//    @Field("amount")
//    public void setAmount(Double amount) {
//        this.amount = amount;
//    }
//
//    @Field("warehouseId")
//    public void setWarehouseId(Long warehouseId) {
//        this.warehouseId = warehouseId;
//    }
//
//    @Field("payType")
//    public void setPayType(PayType payType) {
//        this.payType = payType;
//    }
}
