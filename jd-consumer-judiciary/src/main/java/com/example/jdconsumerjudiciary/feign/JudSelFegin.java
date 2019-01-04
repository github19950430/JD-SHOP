package com.example.jdconsumerjudiciary.feign;


import com.example.jdconsumerjudiciary.entity.Cashdeposit;
import com.example.jdconsumerjudiciary.feign.impl.JudSelImplHy;
import com.example.jdconsumerjudiciary.selpojo.ProductSearch;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "jd-producer-judiciary",fallback = JudSelImplHy.class)
public interface JudSelFegin {

    //司法首页
    @RequestMapping(value = "/judselect",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
    String selectOne(@RequestBody ProductSearch productSearch) throws Exception;

    //商品详情页
    @RequestMapping(value = "/seldetails",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String seldetails(@RequestParam("judid") Integer judid) throws Exception;

    //通过商品ID查询单条商品的保证金   如果没有查不到的话 会返回状态码 "404"
    @RequestMapping(value = "/selectcash",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String selcash(@RequestParam("id") Integer id);

    //根据商品ID 查询 商品名称
    @RequestMapping(value = "/seljudname",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String seljudname(@RequestParam("judid") Integer judid);

    //查询是否有保证金  参数有问题或者没有保证金的话返回null
    @RequestMapping(value = "/cashselectOne",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String selectOnebao(@RequestBody Cashdeposit cashdeposit);


    /**
     * 缴纳商品保证金  需要用户userID 商品ID  商品类型typeID 保证金钱数
     * @param cashdeposit
     * @return
     */
    @RequestMapping(value = "/addcashprice",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String addcash(@RequestBody Cashdeposit cashdeposit);

    /**
     * 查看保证金
     * @param userid
     * @return
     */
    @RequestMapping(value = "/selcashuser",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String selcashbao(@RequestParam("userid") Integer userid);

    /**
     * 得到用户ID 商品ID 加价后的价格 进行对商品加价 和添加出价表
     * @param userid
     * @param judid
     * @return
     */
    @RequestMapping(value = "/updataprice",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String updataprice(@RequestParam("userid") Integer userid,@RequestParam("judid") Integer judid);

    /**
     * 点击设置提醒 对提醒人数加一
     */
    @RequestMapping(value = "/updateRemind",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    String updateRemind(@RequestParam("judid") Integer judid);

    /**
     * 通过商品ID查询出价记录三条数据进行排序
     * @param judid
     * @return
     */
    @RequestMapping(value = "/selbids",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String selbids(@RequestParam("judid") Integer judid);

    /**
     * 0我的竞拍 1 我的获拍
     * @param userid
     * @param state
     * @return
     */
    @RequestMapping(value = "/selmyauction",method = RequestMethod.POST,produces = "text/json;charset=utf-8")
    public String selmyauction(@RequestParam("userid")Integer userid,@RequestParam("state")Integer state);
}
