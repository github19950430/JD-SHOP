package com.example.jdproducerorder.entity.solr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * (OrderDetail)实体类
 *
 * @author 阿飞！
 * @since 2018-12-22 11:22:20
 */
@Data
@ApiModel
public class OrderDetailVo implements Serializable {
    private static final long serialVersionUID = 462526609106412225L;
    private String orderDetailId;

    private String orderNo;

    @ApiModelProperty(value = "商店id", required = true)
    private Integer storeId;

    @ApiModelProperty(value = "商品id", required = true)
    private Integer productId;

    //商店名字
    private String storeName;

    @ApiModelProperty(value = "商品数量", required = true)
    private Integer number;

    @ApiModelProperty(value = "商品名", required = true)
    private String productName;

    @ApiModelProperty(value = "商品单价", required = true)
    private Double productPrice;
    @ApiModelProperty(value = "商品sku", required = true)
    private String peoductSku;
    @ApiModelProperty(value = "运费", required = true)
    private Double logisticsFee;
    //优惠券id
    private Integer couponId;

    @ApiModelProperty(value = "优惠券所属", required = true, notes = "0 ：商家 1：商品")
    private Integer couponTo;

    @ApiModelProperty(value = "优惠券面值", required = true)
    private Integer couponMoney;

    private Integer status;

}