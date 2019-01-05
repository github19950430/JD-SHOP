package com.example.jdproducerjudiciary.entity;

import java.io.Serializable;

/**
 * (Myauction)实体类
 *
 * @author makejava
 * @since 2018-12-28 16:25:30
 */
public class Myauction implements Serializable {
    private static final long serialVersionUID = -25981317247689259L;
    //我的拍品ID
    private Integer myauctionId;
    //拍品ID
    private Integer myauctionShopid;
    //拍品类型
    private Integer myauctionType;
    //拍品下拍时间
    private String myauctionTime;
    //用户ID
    private Integer myauctionUserid;
    //拍品状态 0 拍卖中 1 已获拍
    private Integer myauctionState;

    //-----------------------------------拍卖名称 拍品状态
    private String myjudname; //拍卖名称
    private String mystate; //拍品状态
    private String myprice; //当前价格
    private String mypicture;//拍品图片

    public String getMyjudname() {
        return myjudname;
    }

    public void setMyjudname(String myjudname) {
        this.myjudname = myjudname;
    }

    public String getMystate() {
        return mystate;
    }

    public void setMystate(String mystate) {
        this.mystate = mystate;
    }

    public Integer getMyauctionId() {
        return myauctionId;
    }

    public void setMyauctionId(Integer myauctionId) {
        this.myauctionId = myauctionId;
    }

    public Integer getMyauctionShopid() {
        return myauctionShopid;
    }

    public void setMyauctionShopid(Integer myauctionShopid) {
        this.myauctionShopid = myauctionShopid;
    }

    public Integer getMyauctionType() {
        return myauctionType;
    }

    public void setMyauctionType(Integer myauctionType) {
        this.myauctionType = myauctionType;
    }

    public String getMyauctionTime() {
        return myauctionTime;
    }

    public void setMyauctionTime(String myauctionTime) {
        this.myauctionTime = myauctionTime;
    }

    public Integer getMyauctionUserid() {
        return myauctionUserid;
    }

    public void setMyauctionUserid(Integer myauctionUserid) {
        this.myauctionUserid = myauctionUserid;
    }

    public Integer getMyauctionState() {
        return myauctionState;
    }

    public void setMyauctionState(Integer myauctionState) {
        this.myauctionState = myauctionState;
    }

    public String getMyprice() {
        return myprice;
    }

    public void setMyprice(String myprice) {
        this.myprice = myprice;
    }

    public String getMypicture() {
        return mypicture;
    }

    public void setMypicture(String mypicture) {
        this.mypicture = mypicture;
    }
}