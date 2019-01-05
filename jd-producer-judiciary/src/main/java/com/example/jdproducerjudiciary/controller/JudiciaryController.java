package com.example.jdproducerjudiciary.controller;

import com.alibaba.fastjson.JSON;
import com.example.jdproducerjudiciary.entity.Judiciary;
import com.example.jdproducerjudiciary.selpojo.ProductSearch;
import com.example.jdproducerjudiciary.service.JudiciaryService;
import com.example.jdproducerjudiciary.solr.SearchProductDao;
import com.example.jdproducerjudiciary.solr.Seldetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Judiciary)表控制层
 *
 * @author makejava
 * @since 2018-12-20 21:50:35
 */
@RestController
public class JudiciaryController {
    /**
     * 服务对象
     */
    @Resource
    private JudiciaryService judiciaryService;
    @Resource
    private SearchProductDao searchProductDao;
    @Resource
    private Seldetails seldetails;
    /**
     * 司法首页
     *
     * @return 单条数据
     */
    @RequestMapping(value = "/judselect",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String selectOne(@RequestBody ProductSearch productSearch) throws Exception {
        List<Judiciary> judiciaries = searchProductDao.searchProduct(productSearch);
        return JSON.toJSONString(judiciaries);
    }

    /**
     * 商品详情页
     * @param judid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/seldetails",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String seldetails(@RequestParam("judid") Integer judid,@RequestParam("userid") Integer userid) throws Exception {
        List<Judiciary> judiciaries = seldetails.searchProduct(judid,userid);
        return JSON.toJSONString(judiciaries);
    }
    /**
     * 通过主键查询单条商品的保证金   如果没有查不到的话 会返回状态码 "404"
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectcash",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String selcash(@RequestParam("id") Integer id){
       return judiciaryService.queryById(id);
    }

    /**
     * 根据商品ID 查询 商品名称
     * @param judid
     * @return
     */
    @RequestMapping(value = "/seljudname",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String seljudname(@RequestParam("judid") Integer judid){
        return judiciaryService.queryjudname(judid);
    }


    /**
     * 得到用户ID 商品ID 加价后的价格 进行对商品加价 和添加出价表
     * @param userid
     * @param judid
     * @return
     */
    @RequestMapping(value = "/updataprice",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String updataprice(@RequestParam("userid") Integer userid,@RequestParam("judid") Integer judid){

       return judiciaryService.update(userid,judid);
    }

    /**
     * 点击设置提醒 对提醒人数加一
     */
    @RequestMapping(value = "/updateRemind",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String updateRemind(@RequestParam("judid") Integer judid){
        return judiciaryService.updateRemind(judid);

    }

}