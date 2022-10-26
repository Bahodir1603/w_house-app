package com.example.w_houseapp.solrRepository;

import com.example.w_houseapp.solr.SolrEmployee;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.UUID;

public interface SolrEmployeeRepository extends SolrCrudRepository<SolrEmployee, UUID> {
}
