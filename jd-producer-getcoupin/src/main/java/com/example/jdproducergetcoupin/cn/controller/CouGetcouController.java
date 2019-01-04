package com.example.jdproducergetcoupin.cn.controller;

import com.example.jdproducergetcoupin.cn.service.CouGetcouService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (CouGetcou)表控制层
 *
 * @author makejava
 * @since 2018-12-24 15:14:25
 */
@RestController
public class CouGetcouController {
    /**
     * 服务对象
     */
    @Resource
    private CouGetcouService couGetcouService;

    @GetMapping(value = "/getcoupon", produces = "text/json;charset=utf-8")
    public String getCoupon(Integer ownid, Integer cou_id){
        return couGetcouService.getCoupon(ownid, cou_id);
    }
}