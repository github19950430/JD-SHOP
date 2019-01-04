package com.example.jdproducerorder.entity.order;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (OrderVo)实体类
 *
 * @author 阿飞！
 * @since 2018-12-22 11:22:15
 */
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 616468188699348547L;
    //订单id
    private Integer orderId;
    //订单号
    private String orderNo;
    //未付款,已付款,已发货,已签收,退货申请,退货中,已退货,取消交易
    private Integer orderStatus;
    //商店id
    private Integer storeId;
    //商店名字
    private String storeName;
    //商品总价
    private Double productAmountTotal;
    //订单金额
    private Double orderAmountTotal;
    //优惠金额
    private Double couponMoney;
    //运费金额
    private Double logisticsFee;
    //地址
    private String address;
    //运单号
    private String orderlogisticsNo;
    //订单支付渠道
    private Integer payChannel;
    //订单支付单号
    private String outTradeNo;

    //创建时间
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date createTime;

    //支付时间
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date payTime;

    //客户编号
    private Integer userId;
    //订单结算状态，货到付款，分期结算之类
    private Integer orderSettlementStatus;

    //订单结算时间
//    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
//    private Date orderSettlementTime;

}