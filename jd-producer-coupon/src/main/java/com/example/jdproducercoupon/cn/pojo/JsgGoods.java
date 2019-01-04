package com.example.jdproducercoupon.cn.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;

/**
 * (JsgGoods)实体类
 *
 * @author makejava
 * @since 2018-12-21 13:36:41
 */

@Getter
@Setter
@ToString
public class JsgGoods {
    @Id
    private Integer gId;
    private String gName;
    private Integer gBid;
    private Object gPrice;
    private String gPic;
    private String gDesc;
    private Integer gStatus;
    private Integer gCount;
    private Integer gSales;
    private Integer gGtid;
    private Integer gSeller;
}