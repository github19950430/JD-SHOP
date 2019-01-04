package com.example.jdproducerjudiciary.controller;

import com.example.jdproducerjudiciary.entity.Bids;
import com.example.jdproducerjudiciary.service.BidsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Bids)表控制层
 *
 * @author makejava
 * @since 2018-12-20 21:04:22
 */
@RestController
public class BidsController {
    /**
     * 服务对象
     */
    @Resource
    private BidsService bidsService;

    /**
     * 通过商品ID查询三条数据进行排序
     * @param judid
     * @return
     */
    @RequestMapping(value = "/selbids",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String selbids(@RequestParam("judid") Integer judid){
       return bidsService.queryById(judid);
    }

}