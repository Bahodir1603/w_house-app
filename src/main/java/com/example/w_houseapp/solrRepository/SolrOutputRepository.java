package com.example.w_houseapp.solrRepository;

import com.example.w_houseapp.solr.SolrOutput;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.UUID;

public interface SolrOutputRepository extends SolrCrudRepository<SolrOutput, UUID> {
}
