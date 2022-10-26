package com.example.w_houseapp.solrRepository;

import com.example.w_houseapp.solr.SolrCategory;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface SolrCategoryRepository extends SolrCrudRepository<SolrCategory,Long> {
}
