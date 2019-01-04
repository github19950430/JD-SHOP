package com.example.jdproducerorder.solr;

import com.example.jdproducerorder.entity.solr.OrderDetailVo;
import com.example.jdproducerorder.entity.solr.OrderSearch;
import com.example.jdproducerorder.entity.solr.OrderVo;

import java.util.List;

public interface SolrOrderDao {
     List<OrderVo> searchOrder(OrderSearch orderSearch) throws Exception;


     List<OrderDetailVo> searchOrderDetail(OrderSearch orderSearch) throws Exception;
}
