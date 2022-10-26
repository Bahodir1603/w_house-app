package com.example.w_houseapp.solrRepository;

import com.example.w_houseapp.solr.SolrInputProduct;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.UUID;

public interface SolrInputProductRepository extends SolrCrudRepository<SolrInputProduct, UUID> {
}
