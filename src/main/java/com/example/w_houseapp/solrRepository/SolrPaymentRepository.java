package com.example.w_houseapp.solrRepository;

import com.example.w_houseapp.solr.SolrPayment;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.UUID;

public interface SolrPaymentRepository extends SolrCrudRepository<SolrPayment, UUID> {
}
