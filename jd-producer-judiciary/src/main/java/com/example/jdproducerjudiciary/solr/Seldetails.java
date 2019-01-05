package com.example.jdproducerjudiciary.solr;

import com.example.jdproducerjudiciary.entity.Judiciary;
import com.example.jdproducerjudiciary.selpojo.ProductSearch;

import java.util.List;

public interface Seldetails {
    public List<Judiciary> searchProduct(Integer judid,Integer userid) throws Exception;
}
