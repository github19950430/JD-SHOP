package com.example.jdconsumerorder.feign.impl;

import com.example.jdconsumerorder.feign.OrderFeign;
import com.example.jdproducerorder.entity.solr.OrderVo;
import org.springframework.stereotype.Component;

@Component
public class OrderFeignImpl implements OrderFeign {
    @Override
    public String searchOrder(Integer userid, Integer status, Integer index) throws Exception {
        return null;
    }

    @Override
    public String create(OrderVo orderVo) {
        return null;
    }
}
