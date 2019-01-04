package com.example.jdproducerorder.entity.solr;

import lombok.Data;

@Data
public class OrderSearch {
//    private Integer id; //商品ID
//    private String state; //状态
//    private String sort;  //排序类型
//    private String shopName;//商品名称
//    private String orderNo;//订单编号
    private String queryString;  //关键字
    private Integer pageSize;   //页码
//    private Integer userId;     //用户id

}
