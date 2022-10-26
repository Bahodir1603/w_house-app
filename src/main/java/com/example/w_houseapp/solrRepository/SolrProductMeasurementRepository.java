package com.example.w_houseapp.solrRepository;

import com.example.w_houseapp.solr.SolrProductMeasurement;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.UUID;

public interface SolrProductMeasurementRepository extends SolrCrudRepository<SolrProductMeasurement, UUID> {
}
