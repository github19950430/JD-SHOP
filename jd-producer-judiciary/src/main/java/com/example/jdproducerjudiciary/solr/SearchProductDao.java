package com.example.jdproducerjudiciary.solr;


import com.example.jdproducerjudiciary.entity.Judiciary;
import com.example.jdproducerjudiciary.selpojo.ProductSearch;

import java.util.List;

public interface SearchProductDao {
    public List<Judiciary> searchProduct(ProductSearch productSearch) throws Exception;
}
