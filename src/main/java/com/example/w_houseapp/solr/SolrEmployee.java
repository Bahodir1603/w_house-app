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
public class SolrEmployee {
    @Field("id")
    UUID id;
    @Field("fullName")
    String fullName;
    @Field("age")
    Integer age;
    @Field("salary")
    Double salary;
    @Field("phone")
    String phone;
    @Field("warehouseId")
    Long warehouseId;

//    @Field("id")
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    @Field("fullName")
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    @Field("age")
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    @Field("salary")
//    public void setSalary(Double salary) {
//        this.salary = salary;
//    }
//
//    @Field("phone")
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    @Field("warehouseId")
//    public void setWarehouseId(Long warehouseId) {
//        this.warehouseId = warehouseId;
//    }
}
