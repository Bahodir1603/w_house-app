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
public class SolrSupplier {
    @Field("id")
    UUID id;
    @Field("name")
    String name;
    @Field("phone")
    String phone;
    @Field("active")
    boolean active;

//    @Field("id")
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    @Field("name")
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Field("phone")
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    @Field("active")
//    public void setActive(boolean active) {
//        this.active = active;
//    }
}
