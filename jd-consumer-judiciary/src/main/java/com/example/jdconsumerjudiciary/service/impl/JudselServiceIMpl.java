package com.example.jdconsumerjudiciary.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.jdconsumerjudiciary.entity.Cashdeposit;
import com.example.jdconsumerjudiciary.entity.Judiciary;
import com.example.jdconsumerjudiciary.feign.JudSelFegin;
import com.example.jdconsumerjudiciary.selpojo.ProductSearch;
import com.example.jdconsumerjudiciary.service.JudselService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class JudselServiceIMpl implements JudselService {

    @Resource
    private JudSelFegin judSelFegin;
    @Resource
    private RabbitTemplate rabbitTemplate;


    Cashdeposit cashdeposit = new Cashdeposit();


    //司法首页
    @Override
    public String selJudindex(ProductSearch productSearch) throws Exception {
        String jud = judSelFegin.selectOne(productSearch);
        System.out.println("jud首页" +jud);
        if ("0000".equals(jud)){
            return "服务中断，请重试!";
        }
        return jud;
    }

    //  司法详情需要查看保证金
    @Override
    public String selJud(Integer judid,Integer userid) throws Exception{

        if (userid == null){
            String jud = judSelFegin.seldetails(judid,userid);
            if ("0000".equals(jud)){
                return "服务中断，请重试!";
            }
            List<Judiciary> judiciaries = JSONArray.parseArray(jud, Judiciary.class);
            Judiciary judiciary = judiciaries.get(0);

            return JSON.toJSONString(judiciary);
        }
        cashdeposit.setCashUser(userid);//用户ID
        cashdeposit.setCashType(1);//司法类型ID
        cashdeposit.setCashShopid(judid);//商品ID

        String jud = judSelFegin.seldetails(judid,userid);//展示数据

        String s = judSelFegin.selectOnebao(cashdeposit);//查询用户是否有该商品的保证金
        if ("0000".equals(s) || "0000".equals(jud)){
            return "服务中断，请重试!";
        }
        if(s == null){
            List<Judiciary> judiciaries = JSONArray.parseArray(jud, Judiciary.class);
            Judiciary judiciary = judiciaries.get(0);
            return JSON.toJSONString(judiciary);
        }
        List<Judiciary> judiciaries = JSONArray.parseArray(jud, Judiciary.class);
        Judiciary judiciary = judiciaries.get(0);
        judiciary.setJudCash("200");
        return JSON.toJSONString(judiciary);
    }

    //点击抢购按钮 已交保证金  用户ID 和 商品ID
    @Override
    public String upaddcash(Integer userid, Integer judid) {

        cashdeposit.setCashType(1);
        cashdeposit.setCashUser(userid);
        cashdeposit.setCashShopid(judid);
        String Cash = judSelFegin.selectOnebao(cashdeposit);//保证金表的保证金*/
        String Judcash = judSelFegin.selcash(judid);//司法商品保证金
        if ("0000".equals(Cash) || "0000".equals(Judcash)){
            return "服务中断,请重试!";
        }
        String Cashprice = Cash.replace(",", "");
        String Judcashprice = Judcash.replace(",", "");

        if (Integer.parseInt(Cashprice) == Integer.parseInt(Judcashprice)){
            System.out.println("保证金相同");
            String updataprice = judSelFegin.updataprice(userid, judid);
            if ("0000".equals(updataprice))
                return "服务中断,请重试!";
            return updataprice;
        }
        return "加价失败!请查看该商品的保证金是否符合!";
    }

    /**
     * 缴纳保证金
     *                        //得到用户id 商品ID  商品类型ID自己给
     * @return              //保证金需要查询商品保证金得到
     */
    @Override
    public String addcash(Integer userid, Integer shopid,String dingdanhao) {


        cashdeposit.setCashShopid(shopid);
        cashdeposit.setCashUser(userid);
        cashdeposit.setCashOrderid(dingdanhao);
        String addcash = judSelFegin.addcash(cashdeposit);

        return addcash;

    }

    /**
     * 查看保证金
     * @param userid
     * @return
     */
    @Override
    public String selcash(Integer userid) {

        return judSelFegin.selcashbao(userid);
    }

    /**
     * //设置提醒  对提醒人数加一 同时 设置该用户对这个商品的开拍时间表
     * @param userid
     * @param shopid
     * @return
     */
    @Override
    public String updateRemind(Integer userid, Integer shopid) {

        return null;
    }

    /**
     * 通过商品ID查询出价记录三条数据进行排序
     * @param shopid
     * @return
     */
    @Override
    public String selconbids(Integer shopid) {
        String selbids = judSelFegin.selbids(shopid);
        System.out.println("selbids=" + selbids);
        if ("0000".equals(selbids))
            return "服务中断!请重试!";
        return selbids;
    }

    /**
     * 查看我的拍品  0拍卖中 和 1已获拍
     * @param userid
     * @param state
     * @return
     */
    @Override
    public String selmyauction(Integer userid, Integer state) {
        String selmyauction = judSelFegin.selmyauction(userid, state);
        return selmyauction;
    }
}
