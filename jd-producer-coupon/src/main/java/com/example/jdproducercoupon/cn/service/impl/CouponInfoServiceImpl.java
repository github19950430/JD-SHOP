/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CouponInfoServiceImpl
 * Author:   RanHaoHao
 * Date:     2018/12/21 10:59
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * 　　　　　　　 ┏┓       ┏┓+ +
 * 　　　　　　　┏┛┻━━━━━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　 ┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 █████━█████  ┃+
 * 　　　　　　　┃　　　　　　 ┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　 ┃ + +
 * 　　　　　　　┗━━┓　　　 ┏━┛
 * 　　　　　　　　　┃　　  ┃ + + + +
 * 　　　　　　　　　┃　　　┃　Code is far away from     bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　         神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　 ┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━━━┳┓┏┛ + + + +
 * 　　　　　　　　　 ┃┫┫　 ┃┫┫
 * 　　　　　　　　　 ┗┻┛　 ┗┻┛+ + + + *
 */
package com.example.jdproducercoupon.cn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.jdproducercoupon.cn.mapper.CouGetcouDao;
import com.example.jdproducercoupon.cn.mapper.CouListDao;
import com.example.jdproducercoupon.cn.mapper.CouponInfoDao;
import com.example.jdproducercoupon.cn.mapper.JdShopTypeDao;
import com.example.jdproducercoupon.cn.pojo.CouList;
import com.example.jdproducercoupon.cn.pojo.JdShopType;
import com.example.jdproducercoupon.cn.service.CouponInfoService;
import com.example.jdproducercoupon.cn.util.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author RanHaoHao
 * @create 2018/12/21
 * @since 1.0.0
 */
@Service
public class CouponInfoServiceImpl implements CouponInfoService {
    @Resource
    private CouponInfoDao couponInfoDao;

    @Resource
    private JdShopTypeDao jdShopTypeDao;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private CouGetcouDao couGetcouDao;

    @Resource
    private CouListDao couListDao;

    /**
     * 〈一句话功能简述〉<br>
     * 获取所有优惠券信息
     *
     * @return
     * @author //TODO RanHaoHao
     * @date 2018/12/21 10:59
     */
    @Override
    public String getAllCoupon() {
        List<CouList> couListList = couponInfoDao.selectAll();
        List<CouList> couLists = new ArrayList<>();
        for (CouList coulist : couListList) {
            Example example = new Example(JdShopType.class);
            example.createCriteria()
                    .andEqualTo("typeid", coulist.getCou_shop_type());
            coulist.setCou_shop_type(jdShopTypeDao.selectOneByExample(example).getTypename());
            couLists.add(coulist);
        }
        return JSON.toJSONString(couLists);
    }

    @Override
    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.queue_MinusInventory)
    public void upCoupon(String couponMap) {
        Map<String, Object> couMap = JSONObject.parseObject(couponMap);
        CouList couList = new CouList();
        couList.setCou_id((int)couMap.get("cou_id"));
        couList = couponInfoDao.selectOne(couList);
        System.out.println(JSON.toJSONString(couList));
        if (couList.getCou_amount() > couList.getCou_getamount()) {
            couList.setCou_getamount(couList.getCou_getamount() + 1);
            couponInfoDao.updateByPrimaryKeySelective(couList);
            if (couGetcouDao.insertSelective(couGetcou) > 0) {
                return JSON.toJSONString("恭喜您，领取成功");
            } else {
                return JSON.toJSONString("内部错误，领取失败");
            }
        } else {
            return JSON.toJSONString("优惠券领取失败，请查看余量");
        }
    }
}
