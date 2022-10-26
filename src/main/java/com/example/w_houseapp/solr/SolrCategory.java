package com.example.w_houseapp.solr;

import lombok.*;
import org.apache.solr.client.solrj.beans.Field;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SolrCategory {
    @Field("id")
    Long id;
    @Field("name")
    String name;

//    @Field("id")
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Field("name")
//    public void setName(String name) {
//        this.name = name;
//    }
}
