package com.example.jdproducerorder.solr;

import com.alibaba.fastjson.JSON;
import com.example.jdproducerorder.entity.solr.OrderDetailVo;
import com.example.jdproducerorder.entity.solr.OrderSearch;
import com.example.jdproducerorder.entity.solr.OrderVo;
import lombok.extern.log4j.Log4j;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Log4j
public class SolrOrderDaoImpl implements SolrOrderDao {
    private static final String solrUrl="http://188.131.133.118:8983/solr/order-core";//订单core

    //创建solrClient同时指定超时时间，不指定走默认配置
    private static HttpSolrClient client=new HttpSolrClient.Builder(solrUrl).build();

    @Override
    public List<OrderVo> searchOrder(OrderSearch orderSearch) throws Exception {
        //设置查询对象
        SolrQuery solrQuery = new SolrQuery();
        //params 支持多个fq查询的对象  执行查询时 执行params这个对象
//        ModifiableSolrParams params = new ModifiableSolrParams();
        //设置关键字
        solrQuery.setQuery(orderSearch.getQueryString());

        log.info("关键字 :"+orderSearch.getQueryString());

        //设置默认搜索域
        solrQuery.set("df", "product_keywords");
        /*// 根据ID查
        if (null != orderSearch.getId() && !"".equals(orderSearch.getId())){
            solrQuery.addFilterQuery("id:" + orderSearch.getId());
        }*/
        /*//根据订单编号查询
        if(orderSearch.getOrderNo() != null && !"".equals(orderSearch.getOrderNo()))
            solrQuery.addFilterQuery("order_no:" + orderSearch.getOrderNo());
        //根据商品名字查询
        if(orderSearch.getShopName() != null && !"".equals(orderSearch.getShopName()))
            solrQuery.addFilterQuery("shopName:" + orderSearch.getShopName());
        //根据订单状态查询
        if(orderSearch.getState() != null && !"".equals(orderSearch.getState()))
            solrQuery.addFilterQuery("order_status:" + orderSearch.getState());*/

        solrQuery.setStart(orderSearch.getPageSize());//第几行开始
        solrQuery.setRows(5);//一页显示多少行

        // 执行查询
//        params.add(solrQuery);

        QueryResponse response = client.query(solrQuery);
        log.info("response" + response);
        // 文档结果集
        SolrDocumentList docs = response.getResults();
        log.info(JSON.toJSONString(docs));

        List<OrderVo> orderVos = new ArrayList<>();
        for (SolrDocument doc : docs) {
            OrderVo orderVo = new OrderVo();
            orderVo.setStoreName((String) doc.get("store_name"));
//            orderVo.setOrderId(Integer.parseInt((String) doc.get("id")));
            orderVo.setOrderNo((String) doc.get("order_no"));
            orderVo.setOrderStatus(Integer.parseInt((String) doc.get("order_status")));
            orderVo.setUserId(Integer.parseInt ((String) doc.get("user_id")));
            orderVo.setProductAmountTotal(Double.parseDouble((String) doc.get("product_amount_total")));
            orderVo.setOrderAmountTotal(Double.parseDouble((String) doc.get("order_amount_total")));
            orderVo.setCouponMoneyTotal(Double.parseDouble((String) doc.get("coupon_money_total")));
            orderVo.setUserName((String) doc.get("user_name"));
            orderVo.setAddress((String) doc.get("address"));
            orderVo.setOrderlogisticsNo((String) doc.get("orderlogistics_no"));
            orderVo.setLogisticsFeeTotal(Double.parseDouble((String) doc.get("logistics_fee")));
            orderVo.setOutTradeNo((String) doc.get("out_trade_no"));
            log.info((String) doc.get("create_time") + "++++++++++++++++++++++++");
            orderVo.setCreateTime(new Date((String) doc.get("create_time")));
            orderVo.setPayChannel(Integer.parseInt((String) doc.get("pay_channel")));
            orderVo.setPayTime((String) doc.get("pay_time"));
            orderVo.setProductAmountTotal(Double.parseDouble((String) doc.get("product_amount_total")));
            orderVos.add(orderVo);
        }

        /*
        以下三行不要删除，设置了总条数，分页使用
         */
        OrderVo orderVo = new OrderVo();
        orderVo.setStoreName(docs.getNumFound() + "");
        orderVos.add(orderVo);
        return orderVos;
    }

    @Override
    public List<OrderDetailVo> searchOrderDetail(OrderSearch orderSearch) throws Exception {
        //设置查询对象
        SolrQuery solrQuery = new SolrQuery();

        //设置关键字
        solrQuery.setQuery(orderSearch.getQueryString());

        log.info("关键字 :"+orderSearch.getQueryString());

        //设置默认搜索域
        solrQuery.set("df", "product_keywords");
        solrQuery.setStart(orderSearch.getPageSize());//第几行开始
        solrQuery.setRows(5);//一页显示多少行
        QueryResponse response = client.query(solrQuery);
        // 文档结果集
        SolrDocumentList docs = response.getResults();
        log.info(JSON.toJSONString(docs));

        List<OrderDetailVo> orderDetails = new ArrayList<>();
        for (SolrDocument doc : docs) {
            OrderDetailVo orderDetail = new OrderDetailVo();
            orderDetail.setOrderDetailId((String)doc.get("id"));
            orderDetail.setOrderNo((String) doc.get("order_no"));
            orderDetail.setProductName((String) doc.get("product_name"));
            orderDetail.setPeoductSku((String) doc.get("product_sku"));
            orderDetail.setProductPrice(Double.parseDouble((String) doc.get("product_price")));
            log.info("get num is " + doc.get("number"));
            orderDetail.setNumber(Integer.parseInt((String) doc.get("number")));
//            orderDetail.setStatus(Integer.parseInt((String) doc.get("status")));
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }
}
