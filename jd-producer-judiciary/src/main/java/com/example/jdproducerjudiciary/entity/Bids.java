package com.example.jdproducerjudiciary.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Bids)实体类
 *
 * @author makejava
 * @since 2018-12-20 21:04:19
 */
public class Bids implements Serializable {
    private static final long serialVersionUID = -99005712882941320L;
    //出价记录表ID
    private Integer bidsId;
    //司法商品ID
    private Integer bidsGoods;
    //出价人ID
    private Integer bidsUserid;
    //出价金额
    private String bidsPrice;
    //出价时间
    private String bidsTime;


    public Integer getBidsId() {
        return bidsId;
    }

    public void setBidsId(Integer bidsId) {
        this.bidsId = bidsId;
    }

    public Integer getBidsGoods() {
        return bidsGoods;
    }

    public void setBidsGoods(Integer bidsGoods) {
        this.bidsGoods = bidsGoods;
    }

    public Integer getBidsUserid() {
        return bidsUserid;
    }

    public void setBidsUserid(Integer bidsUserid) {
        this.bidsUserid = bidsUserid;
    }

    public String getBidsPrice() {
        return bidsPrice;
    }

    public void setBidsPrice(String bidsPrice) {
        this.bidsPrice = bidsPrice;
    }

    public String getBidsTime() {
        return bidsTime;
    }

    public void setBidsTime(String bidsTime) {
        this.bidsTime = bidsTime;
    }
}