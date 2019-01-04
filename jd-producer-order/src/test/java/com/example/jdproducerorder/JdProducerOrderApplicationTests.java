package com.example.jdproducerorder;

import com.example.jdproducerorder.entity.solr.OrderVo;
import com.example.jdproducerorder.service.solrservice.SolrOrderService;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdProducerOrderApplicationTests {

    @Resource
    private SolrOrderService solrOrderService;

    @Test
    public void contextLoads() throws IOException, SolrServerException {
        solrOrderService.queryAll();
    }

    @Test
    public void test(){
        OrderVo orderVo = new OrderVo();
        orderVo.setUserId(5);
        System.out.println(orderVo.getUserId());
        test1(orderVo);
        System.out.println(orderVo.getUserId());
    }

    private void test1(OrderVo orderVo){
        orderVo.setUserId(1);
    }

}

