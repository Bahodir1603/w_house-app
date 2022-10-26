package com.example.w_houseapp.solr;

import com.example.w_houseapp.entity.Supplier;
import com.example.w_houseapp.entity.Warehouse;
import com.example.w_houseapp.entity.enums.PayType;
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

public class SolrExpense {
    @Field("id")
    UUID id;
    @Field("description")
    String description;
    @Field("amount")
    Double amount;
    @Field("supplierId")
    UUID supplierId;
    @Field("warehouseId")
    Long warehouseId;
    @Field("payType")
    PayType payType;

//    @Field("id")
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    @Field("description")
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    @Field("amount")
//    public void setAmount(Double amount) {
//        this.amount = amount;
//    }
//
//    @Field("supplierId")
//    public void setSupplierId(UUID supplierId) {
//        this.supplierId = supplierId;
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
