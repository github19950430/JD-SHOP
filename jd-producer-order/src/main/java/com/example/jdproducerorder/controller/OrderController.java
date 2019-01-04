package com.example.jdproducerorder.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.jdproducerorder.entity.order.Order;
import com.example.jdproducerorder.entity.solr.OrderVo;
import com.example.jdproducerorder.service.OrderService;
import com.example.jdproducerorder.service.solrservice.SolrOrderService;
import com.example.jdproducerorder.utils.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private SolrOrderService solrOrderService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "搜索订单",notes = "通过状态")
    @GetMapping(value = "/search")
    public String searchOrder(@RequestParam("userid") Integer userid,
                              @RequestParam("status") Integer status,
                              @RequestParam("index") Integer index) throws Exception {
        return orderService.searchOrder(userid, status, index).toJSONString();
    }

    @ApiOperation(value = "搜索订单",notes = "通过用户id")
    @GetMapping(value = "/searchAll")
    public String searchOrder(@RequestParam Integer userid,
                              @RequestParam Integer index) throws Exception {

        return orderService.searchOrderAll(userid,index).toJSONString();
    }

    @ApiOperation(value = "搜索订单", notes = "通过单号确定唯一的订单")
    @GetMapping(value = "/searchone")
    public String searchOrder(@RequestParam Integer userid,
                              @RequestParam Integer status,
                              @RequestParam String orderNo) throws Exception{
        return JSON.toJSONString(orderService.searchOrder(userid, status, orderNo));
    }

    @PostMapping(value = "/create")
    public String create(@RequestBody OrderVo orderVo){
        List<OrderVo> order = orderService.createOrder(orderVo);
        return JSON.toJSONString(order);
    }

    @ApiOperation(value = "修改订单状态",
            notes = "只能修改已付款之后的状态 //0 未付款,1 已付款, 2 已发货,3 已签收, 4 退货申请,5 退货中,6 已退货,7 取消交易")
    @PostMapping("/updateStatus")
    public String update(String orderNo, Integer status){
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setOrderStatus(status);
        return orderService.updateOrder(order) + "";
    }


    @ApiOperation(value = "将未付款更改为已付款")
    @PostMapping("/noPayment2payment")
    public String noPayment2payment(@RequestParam("orderNo") String orderNo,
                                    @RequestParam("userId") Integer userId,
                                    @RequestParam("payNo") String payNo,
                                    @RequestParam("payTime") String payTime){
        return orderService.noPayment2payment(orderNo, userId, payNo, payTime) + "";
    }

    @ApiOperation(value = "清空solr中的数据")
    @GetMapping("deleteAll")
    public String deleteAllSolrDate(){
        try {
            solrOrderService.deleteAll();
        } catch (IOException e) {
            e.printStackTrace();
            return "empty fail";
        } catch (SolrServerException e) {
            e.printStackTrace();
            return "empty fail";
        }
        return "empty success!";
    }
}
