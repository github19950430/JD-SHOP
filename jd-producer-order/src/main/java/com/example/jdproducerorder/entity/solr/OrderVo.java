package com.example.jdproducerorder.entity.solr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (OrderVo)实体类
 *
 * @author 阿飞！
 * @since 2018-12-22 11:22:15
 */
@Data
@ApiModel
public class OrderVo implements Serializable {

    @ApiModelProperty(hidden = true)
    private static final long serialVersionUID = 616468188699348547L;
    @ApiModelProperty(hidden = true)
    private Integer orderId;
    //订单号
    private String orderNo;
    //未付款,已付款,已发货,已签收,退货申请,退货中,已退货,取消交易
    private Integer orderStatus;
    //商店名字
    private String storeName;
    //商品总价
    private Double productAmountTotal;
    //运费总价
    private Double logisticsFeeTotal;
    //优惠总额
    private Double couponMoneyTotal;
    //订单金额
    private Double orderAmountTotal;

    @ApiModelProperty(value = "用户id",required = true)
    private Integer userId;
    @ApiModelProperty(value = "用户名字",required = true)
    private String userName;
    @ApiModelProperty(value = "地址信息",required = true)
    private String address;
    //订单物流编号
    private String orderlogisticsNo;
    @ApiModelProperty(value = "支付渠道",required = true)
    private Integer payChannel;
    //订单支付单号
    private String outTradeNo;
    //支付时间
    private String payTime;
    //创建时间
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    //订单结算状态，货到付款，分期结算之类
    private Integer orderSettlementStatus;
    //订单结算时间
    //private String orderSettlementTime;
    //订单详情（包含的商品）
    private List<OrderDetailVo> orderDetailVos;

}