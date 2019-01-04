package com.example.jdproducerorder.service.solrservice;

import com.example.jdproducerorder.entity.order.Order;
import com.example.jdproducerorder.entity.order.OrderDetail;
import com.example.jdproducerorder.entity.solr.OrderVo;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface SolrOrderService {

    /**
     * 查询多条数据 导到solr
     *
     * @return 对象列表
     */
    void queryAllDB2Solr() throws IOException, SolrServerException;

    /**
     * 删除solr中所有的数据
     * @return
     */
    String deleteAll() throws IOException, SolrServerException;

    /**
     * 删除solr中的数据通过订单号
     * @param no
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    String deleteByNo(String userid, String no) throws IOException, SolrServerException;

    String updateByNo(OrderVo orderVo) throws Exception;

    String create(OrderVo orderVo) throws IOException, SolrServerException;

    String create(List<Order> orders, List<OrderDetail> orderDetails) throws IOException, SolrServerException;

}
