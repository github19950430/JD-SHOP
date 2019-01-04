package com.example.jdconsumerjudiciary.selpojo;
/**
 * 搜索商品的实体类
 * @author scw
 *
 */
public class ProductSearch {

    private Integer id; //商品ID
    private String catalog_name; //类别
    private String price;  //价格
    private String site; //所在地
    private String state; //状态  拍卖中 预告中 已结束 已暂缓 已中止 已撤回
    private String sort;  //排序类型
    private String stage; //拍卖阶段    一拍 二拍 变卖
    private String queryString;  //关键字
    private Integer pageSize;   //页码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getQueryString() {
        return queryString;
    }
    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
    public String getCatalog_name() {
        return catalog_name;
    }
    public void setCatalog_name(String catalog_name) {
        this.catalog_name = catalog_name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
