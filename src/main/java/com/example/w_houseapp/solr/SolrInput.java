package com.example.w_houseapp.solr;

import com.example.w_houseapp.entity.enums.Currency;
import lombok.*;
import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SolrInput {
    @Field("id")
    UUID id;
    @Field("supplierId")
    UUID supplierId;
    @Field("date")
    Date date;
    @Field("facture_number")
    Integer facture_number;
    @Field("warehouseId")
    Long warehouseId;
    @Field("currency")
    Currency currency;

//    @Field("id")
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    @Field("supplierId")
//    public void setSupplierId(UUID supplierId) {
//        this.supplierId = supplierId;
//    }
//
//    @Field("date")
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    @Field("facture_number")
//    public void setFacture_number(Integer facture_number) {
//        this.facture_number = facture_number;
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
