package com.example.jdproducerorder.entity.order;

import lombok.Data;

import java.io.Serializable;

/**
 * (OrderDetail)实体类
 *
 * @author 阿飞！
 * @since 2018-12-22 11:22:20
 */
@Data
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 462526609106412225L;
    //订单详情id
    private Integer orderDetailId;
    //订单编号
    private String orderNo;
    //订单商品id
    private Integer productId;
    //商品数量
    private Integer number;
    //商品名
    private String productName;
    //商品单价
    private Double productPrice;
    //商品sku
    private String productSku;
    ////未付款,已付款,已发货,已签收,退货申请,退货中,已退货,取消交易
    private Integer status;

}