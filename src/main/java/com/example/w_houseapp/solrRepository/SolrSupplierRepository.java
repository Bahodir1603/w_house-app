package com.example.w_houseapp.solrRepository;

import com.example.w_houseapp.solr.SolrSupplier;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.UUID;

public interface SolrSupplierRepository extends SolrCrudRepository<SolrSupplier, UUID> {
}
