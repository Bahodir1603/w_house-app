package com.example.w_houseapp.solrRepository;

import com.example.w_houseapp.solr.SolrCustomer;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.UUID;

public interface SolrCustomerRepository extends SolrCrudRepository<SolrCustomer, UUID> {
}
