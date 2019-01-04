package com.example.jdproducerjudiciary.controller;

import com.example.jdproducerjudiciary.entity.Cashdeposit;
import com.example.jdproducerjudiciary.service.CashdepositService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Cashdeposit)表控制层
 *
 * @author makejava
 * @since 2018-12-26 11:55:44
 */
@RestController
public class CashdepositController {
    /**
     * 服务对象
     */
    @Resource
    private CashdepositService cashdepositService;

    /**
     * 通过主键查询单条数据  查询是否有保证金  参数有问题或者没有的话返回null
     *
     * @return 单条数据
     */
    @RequestMapping(value = "/cashselectOne",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String selectOnebao(@RequestBody Cashdeposit cashdeposit) {
        return this.cashdepositService.queryById(cashdeposit);
    }

    /**
     * 缴纳商品保证金  需要用户userID 商品ID  商品类型typeID 保证金钱数
     * @param cashdeposit
     * @return
     */
    @RequestMapping(value = "/addcashprice",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String addcash(@RequestBody Cashdeposit cashdeposit){
       return cashdepositService.insert(cashdeposit);
    }

    /**
     * 查看保证金
     * @param userid
     * @return
     */
    @RequestMapping(value = "/selcashuser",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String selcashbao(@RequestParam("userid") Integer userid){

       return cashdepositService.queryByuserid(userid);
    }
}