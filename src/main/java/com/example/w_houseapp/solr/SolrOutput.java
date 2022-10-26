package com.example.w_houseapp.solr;

import com.example.w_houseapp.entity.Customer;
import com.example.w_houseapp.entity.Warehouse;
import com.example.w_houseapp.entity.enums.Currency;
import com.example.w_houseapp.entity.template.AbsEntity;
import lombok.*;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SolrOutput {
    @Field("id")
    UUID id;
    @Field("customerId")
    UUID customerId;
    @Field("date")
    Date date;
    @Field("warehouseId")
    Long warehouseId;
    @Field("currency")
    Currency currency;

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
//    @Field("date")
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    @Field("warehouseId")
//    public void setWarehouseId(Long warehouseId) {
//        this.warehouseId = warehouseId;
//    }
//
//    @Field("currency")
//    public void setCurrency(Currency currency) {
//        this.currency = currency;
//    }
}
