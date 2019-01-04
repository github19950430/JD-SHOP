package com.example.jdproducerjudiciary.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Judiciary)实体类
 *
 * @author makejava
 * @since 2018-12-21 18:19:24
 */
public class Judiciary implements Serializable {
    private static final long serialVersionUID = -63084107378468966L;
    //司法拍品ID
    private Integer judId;
    //拍品类别
    private Integer judCategory;
    //省市 山西
    private String judAreacity;
    //拍品状态1拍卖中2预告中3已结束4已暂缓5已中止6已撤回
    private Integer judStatus;
    //拍的状态(几拍) 一拍 二拍 变卖 三种
    private Integer judFrequency;
    //司法拍品名
    private String judTradename;
    //拍品地址
    private String judSite;
    //经度
    private String judLongitude;
    //维度
    private String judDimensionality;
    //拍卖开始时间
    private String judAuctiontime;
    //结束时间
    private String judTimeout;
    //报名人数
    private Integer judWillnum;
    //围观人数
    private Integer judOnlooker;
    //当前价
    private String judCurrentprice;
    //起拍价
    private String judStartingprice;
    //评估价
    private String judValuation;
    //加价幅度
    private String judBidincrement;
    //保证金
    private String judCashdeposit;
    //法院公告
    private String judAnnouncement;
    //被设置提醒次数
    private Integer judRemind;
    //图片IP地址图片之间用逗号隔开
    private String judPicture;
    //处置单位
    private String judDisposeunit;
    //联系人加电话  空格隔开
    private String judLinkman;


    public Integer getJudId() {
        return judId;
    }

    public void setJudId(Integer judId) {
        this.judId = judId;
    }

    public Integer getJudCategory() {
        return judCategory;
    }

    public void setJudCategory(Integer judCategory) {
        this.judCategory = judCategory;
    }

    public String getJudAreacity() {
        return judAreacity;
    }

    public void setJudAreacity(String judAreacity) {
        this.judAreacity = judAreacity;
    }

    public Integer getJudStatus() {
        return judStatus;
    }

    public void setJudStatus(Integer judStatus) {
        this.judStatus = judStatus;
    }

    public Integer getJudFrequency() {
        return judFrequency;
    }

    public void setJudFrequency(Integer judFrequency) {
        this.judFrequency = judFrequency;
    }

    public String getJudTradename() {
        return judTradename;
    }

    public void setJudTradename(String judTradename) {
        this.judTradename = judTradename;
    }

    public String getJudSite() {
        return judSite;
    }

    public void setJudSite(String judSite) {
        this.judSite = judSite;
    }

    public String getJudLongitude() {
        return judLongitude;
    }

    public void setJudLongitude(String judLongitude) {
        this.judLongitude = judLongitude;
    }

    public String getJudDimensionality() {
        return judDimensionality;
    }

    public void setJudDimensionality(String judDimensionality) {
        this.judDimensionality = judDimensionality;
    }

    public String getJudAuctiontime() {
        return judAuctiontime;
    }

    public void setJudAuctiontime(String judAuctiontime) {
        this.judAuctiontime = judAuctiontime;
    }

    public String getJudTimeout() {
        return judTimeout;
    }

    public void setJudTimeout(String judTimeout) {
        this.judTimeout = judTimeout;
    }

    public Integer getJudWillnum() {
        return judWillnum;
    }

    public void setJudWillnum(Integer judWillnum) {
        this.judWillnum = judWillnum;
    }

    public Integer getJudOnlooker() {
        return judOnlooker;
    }

    public void setJudOnlooker(Integer judOnlooker) {
        this.judOnlooker = judOnlooker;
    }

    public String getJudCurrentprice() {
        return judCurrentprice;
    }

    public void setJudCurrentprice(String judCurrentprice) {
        this.judCurrentprice = judCurrentprice;
    }

    public String getJudStartingprice() {
        return judStartingprice;
    }

    public void setJudStartingprice(String judStartingprice) {
        this.judStartingprice = judStartingprice;
    }

    public String getJudValuation() {
        return judValuation;
    }

    public void setJudValuation(String judValuation) {
        this.judValuation = judValuation;
    }

    public String getJudBidincrement() {
        return judBidincrement;
    }

    public void setJudBidincrement(String judBidincrement) {
        this.judBidincrement = judBidincrement;
    }

    public String getJudCashdeposit() {
        return judCashdeposit;
    }

    public void setJudCashdeposit(String judCashdeposit) {
        this.judCashdeposit = judCashdeposit;
    }

    public String getJudAnnouncement() {
        return judAnnouncement;
    }

    public void setJudAnnouncement(String judAnnouncement) {
        this.judAnnouncement = judAnnouncement;
    }

    public Integer getJudRemind() {
        return judRemind;
    }

    public void setJudRemind(Integer judRemind) {
        this.judRemind = judRemind;
    }

    public String getJudPicture() {
        return judPicture;
    }

    public void setJudPicture(String judPicture) {
        this.judPicture = judPicture;
    }

    public String getJudDisposeunit() {
        return judDisposeunit;
    }

    public void setJudDisposeunit(String judDisposeunit) {
        this.judDisposeunit = judDisposeunit;
    }

    public String getJudLinkman() {
        return judLinkman;
    }

    public void setJudLinkman(String judLinkman) {
        this.judLinkman = judLinkman;
    }

}