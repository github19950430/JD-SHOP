package com.example.jdproducerjudiciary.controller;

import com.example.jdproducerjudiciary.entity.Myauction;
import com.example.jdproducerjudiciary.service.MyauctionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Myauction)表控制层
 *
 * @author makejava
 * @since 2018-12-28 16:25:30
 */
@RestController
public class MyauctionController {
    /**
     * 服务对象
     */
    @Resource
    private MyauctionService myauctionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/asdselectOne")
    public Myauction selectOne(Integer id) {
        return this.myauctionService.queryById(id);
    }


    /**
     * 0我的竞拍 1 我的获拍
     * @param userid
     * @param state
     * @return
     */
    @RequestMapping(value = "/selmyauction",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String selmyauction(@RequestParam("userid")Integer userid,@RequestParam("state")Integer state){
       return myauctionService.queryAllByLimit(userid,state);
    }
}