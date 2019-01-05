package com.example.jdproducerjudiciary.entity;

import java.io.Serializable;

/**
 * (Cashdeposit)实体类
 *
 * @author makejava
 * @since 2018-12-26 17:24:08
 */
public class Cashdeposit implements Serializable {
    private static final long serialVersionUID = 167500280854292649L;
    //保证金表ID
    private Integer cashId;
    //保证金订单号
    private String cashOrderid;
    //支付宝交易单号
    private String cashTradeno;
    //用户ID
    private Integer cashUser;
    //商品ID
    private Integer cashShopid;
    //拍品类型 1 司法 2 海关 3珍品
    private Integer cashType;
    //保证金状态
    private Integer cashState;
    //保证金
    private String cashPrice;

    //********************************

    //拍卖商品名称
    private String judname;

    public String getJudname() {
        return judname;
    }

    public void setJudname(String judname) {
        this.judname = judname;
    }



    public Integer getCashId() {
        return cashId;
    }

    public void setCashId(Integer cashId) {
        this.cashId = cashId;
    }

    public String getCashOrderid() {
        return cashOrderid;
    }

    public void setCashOrderid(String cashOrderid) {
        this.cashOrderid = cashOrderid;
    }

    public String getCashTradeno() {
        return cashTradeno;
    }

    public void setCashTradeno(String cashTradeno) {
        this.cashTradeno = cashTradeno;
    }

    public Integer getCashUser() {
        return cashUser;
    }

    public void setCashUser(Integer cashUser) {
        this.cashUser = cashUser;
    }

    public Integer getCashShopid() {
        return cashShopid;
    }

    public void setCashShopid(Integer cashShopid) {
        this.cashShopid = cashShopid;
    }

    public Integer getCashType() {
        return cashType;
    }

    public void setCashType(Integer cashType) {
        this.cashType = cashType;
    }

    public Integer getCashState() {
        return cashState;
    }

    public void setCashState(Integer cashState) {
        this.cashState = cashState;
    }

    public String getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(String cashPrice) {
        this.cashPrice = cashPrice;
    }

}