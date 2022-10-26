package com.example.w_houseapp.solrRepository;

import com.example.w_houseapp.entity.Product;
import com.example.w_houseapp.solr.SolrProduct;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface SolrProductRepository extends SolrCrudRepository<SolrProduct,Long> {
    List<SolrProduct> findAllByNameContainingIgnoreCase(String name);
}
