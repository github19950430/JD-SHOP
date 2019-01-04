package com.example.jdconsumerjudiciary.controller;

import com.alibaba.fastjson.JSON;
import com.example.jdconsumerjudiciary.selpojo.ProductSearch;
import com.example.jdconsumerjudiciary.service.JudselService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin(value = "*", allowCredentials = "true")
public class SelJudController {

    @Resource
    private JudselService judselService;

    //  司法拍卖首页
    @RequestMapping(value = "/selJudindex",method = RequestMethod.GET,produces = "text/json;charset=utf-8")
    public String selJudindex(ProductSearch productSearch) throws Exception {
        System.out.println(productSearch.getQueryString()+"**" + productSearch.getCatalog_name() +"**"+ productSearch.getSite() +"**"+ productSearch.getPageSize()+"**" + productSearch.getState() +"**"+ productSearch.getStage());
        return judselService.selJudindex(productSearch);
    }

    //  司法详情页
    @RequestMapping(value = "/selJuditem",method = RequestMethod.GET,produces = "text/json;charset=utf-8")
    public String selJudItem(Integer id,Integer JWT) throws Exception{
        return judselService.selJud(id,JWT);
    }

    //  点击加价接口   加价 需要用户ID 和 商品ID ***************************
    @RequestMapping(value = "/judgecash",method = RequestMethod.GET,produces = "text/json;charset=utf-8")
    public String cash(Integer userid,Integer judid){
        return JSON.toJSONString(judselService.upaddcash(userid,judid));
    }

    //  根据用户ID查看用户多少缴纳的保证金
    @RequestMapping(value = "/slecashuserid",method = RequestMethod.GET,produces = "text/json;charset=utf-8")
    public String selcash(Integer userid){
       return judselService.selcash(userid);
    }


    //点击设置提醒  对该商品的提醒人数 进行加一
    @RequestMapping(value = "/updateRemind",method = RequestMethod.GET,produces = "text/json;charset=utf-8")
    public String updateRemind(Integer userid,Integer judid){
        return judselService.updateRemind(userid,judid);
    }

    //通过商品ID查询出价记录三条数据进行排序
    @RequestMapping(value = "/selconbids",method = RequestMethod.GET,produces = "text/json;charset=utf-8")
    public String selbids(Integer judid){
        System.out.println("judid=" + judid);
       return judselService.selconbids(judid);
    }
}
