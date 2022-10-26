package com.example.w_houseapp.solr;

import lombok.*;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@SolrDocument(solrCoreName = "mycol1")
public class SolrWarehouse {
    @Field("id")
    long id;
    @Field("name")
    String name;
    @Field("active")
    boolean active;

//    @Field("id")
//    public void setId(Long id) {
//        this.id = id;
//    }
//    @Field("name")
//    public void setName(String name) {
//        this.name = name;
//    }
//    @Field("active")
//    public void setActive(boolean active) {
//        this.active = active;
//    }
}
