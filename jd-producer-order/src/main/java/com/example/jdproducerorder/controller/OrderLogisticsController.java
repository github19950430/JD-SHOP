package com.example.jdproducerorder.controller;

import com.alibaba.fastjson.JSON;
import com.example.jdproducerorder.entity.order.OrderLogistics;
import com.example.jdproducerorder.service.OrderLogisticsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (OrderLogistics)表控制层
 *
 * @author 阿飞！
 * @since 2018-12-26 10:13:58
 */
@RestController
@RequestMapping("orderLogistics")
public class OrderLogisticsController {
    /**
     * 服务对象
     */
    @Resource
    private OrderLogisticsService orderLogisticsService;


    @PostMapping("sendout")
    public String sendOut(OrderLogistics logistics){
        try {
            return JSON.toJSONString(orderLogisticsService.sendOut(logistics));
        } catch (Exception e) {
            return JSON.toJSONString(e.getMessage());
        }
    }

    @PostMapping("confirmReceipt")
    public String confirmReceipt(String logisticsNo) {
        try {
            return JSON.toJSONString(orderLogisticsService.confirmReceipt(logisticsNo));
        } catch (Exception e) {
            return JSON.toJSONString(e.getMessage());
        }
    }

}