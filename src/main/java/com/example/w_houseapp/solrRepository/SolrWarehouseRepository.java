package com.example.w_houseapp.solrRepository;

import com.example.w_houseapp.solr.SolrWarehouse;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface SolrWarehouseRepository extends SolrCrudRepository<SolrWarehouse,Long> {
    List<SolrWarehouse> findAllByNameContainingIgnoreCase(String search);
}
