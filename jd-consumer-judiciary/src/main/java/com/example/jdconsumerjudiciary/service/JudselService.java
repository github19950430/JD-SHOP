package com.example.jdconsumerjudiciary.service;

import com.example.jdconsumerjudiciary.entity.Cashdeposit;
import com.example.jdconsumerjudiciary.selpojo.ProductSearch;



public interface JudselService {

    //司法首页
    String selJudindex(ProductSearch productSearch) throws Exception;
    //司法详情需要查看保证金
    String selJud(Integer judid,Integer userid) throws Exception;
    //点击加价接口  加价
    String upaddcash(Integer userid, Integer judid);
    //缴纳保证金
    String addcash(Integer userid, Integer shopid,String dingdanhao);

    //查看保证金
    String selcash(Integer userid);

    //设置提醒  对提醒人数加一 同时 设置该用户对这个商品的开拍时间表
    String updateRemind(Integer userid, Integer shopid);

    //查询商品出价记录表 三条 排序
    String selconbids(Integer shopid);
}
