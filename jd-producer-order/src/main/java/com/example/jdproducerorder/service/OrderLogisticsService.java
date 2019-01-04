package com.example.jdproducerorder.service;

import com.example.jdproducerorder.entity.order.OrderLogistics;
import java.util.List;

/**
 * (OrderLogistics)表服务接口
 *
 * @author 阿飞！
 * @since 2018-12-26 10:13:58
 */
public interface OrderLogisticsService {

    /**
     * 确认发货
     * @param orderLogistics
     * @return
     */
    OrderLogistics sendOut(OrderLogistics orderLogistics) throws Exception;

    /**
     * 确认收货
     * @return
     */
    int confirmReceipt(String logisticsNo) throws Exception;

    /**
     * 修改数据
     *
     * @param orderLogistics 实例对象
     * @return 实例对象
     */
    OrderLogistics update(OrderLogistics orderLogistics);


}