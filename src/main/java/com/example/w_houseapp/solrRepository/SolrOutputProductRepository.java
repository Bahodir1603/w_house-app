package com.example.w_houseapp.solrRepository;

import com.example.w_houseapp.solr.SolrOutputProduct;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.UUID;

public interface SolrOutputProductRepository extends SolrCrudRepository<SolrOutputProduct, UUID> {
}
