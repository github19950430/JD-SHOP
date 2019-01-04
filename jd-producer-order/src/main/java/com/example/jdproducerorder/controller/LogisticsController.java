package com.example.jdproducerorder.controller;

import com.example.jdproducerorder.utils.LogisticsCompany;
import com.example.jdproducerorder.utils.LogisticsUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("Logistics")
public class LogisticsController {


    @GetMapping("query")
    public String queryLogistics(LogisticsCompany logisticsCompany, String logisticsNo){
        return LogisticsUtil.queryData(logisticsCompany.type, logisticsNo);
    }
}
