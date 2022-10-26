package com.example.w_houseapp.solrRepository;

import com.example.w_houseapp.solr.SolrInput;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.UUID;

public interface SolrInputRepository extends SolrCrudRepository<SolrInput, UUID> {
}
