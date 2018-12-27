/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CouGetcouServiceImpl
 * Author:   RanHaoHao
 * Date:     2018/12/26 17:09
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
import com.alibaba.fastjson.JSONArray;
import com.example.jdproducercoupon.cn.mapper.CouGetcouDao;
import com.example.jdproducercoupon.cn.mapper.CouponInfoDao;
import com.example.jdproducercoupon.cn.pojo.CouGetcou;
import com.example.jdproducercoupon.cn.pojo.CouList;
import com.example.jdproducercoupon.cn.pojo.JsgGoods;
import com.example.jdproducercoupon.cn.service.CouGetcouService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author RanHaoHao
 * @create 2018/12/26 17:09
 * @since 1.0.0
 */
@Service
public class CouGetcouServiceImpl implements CouGetcouService {

    @Resource
    private CouGetcouDao couGetcouDao;

    @Resource
    private CouponInfoDao couponInfoDao;

    @Override
    public String selSingleHasCoupon(Integer ownid) {
        CouGetcou couGetcou = new CouGetcou();
        couGetcou.setCou_ownid(ownid);
        return JSON.toJSONString(couGetcouDao.select(couGetcou));
    }

    @Override
    public String orderUseCoupon(String couShopType, Integer ownid) {
        //初始化返回列表的集合
        List<CouList> couLists = new ArrayList<>();

        //根据ownid查询所拥有的优惠券集合
        CouGetcou couGetcou = new CouGetcou();
        couGetcou.setCou_ownid(ownid);
        List<CouGetcou> couListList = couGetcouDao.select(couGetcou);

        //把页面的订单所拥有的物品种类 JSON转 list
        List<JsgGoods> jsgGoodsList = JSONArray.parseArray(couShopType, JsgGoods.class);

        //查询个人优惠券的类别
        CouList couList = new CouList();
        couList.setCou_id(couGetcou.getCou_lid());
        List<CouList> couLists1 = couponInfoDao.select(couList);

        //遍历个人拥有的优惠券
        for (CouGetcou couGetCouList : couListList) {
            for (CouList couList1 : couLists1) {
                if (couList1.getCou_id().equals(couGetCouList.getCou_lid())) {
                    for (JsgGoods shopType : jsgGoodsList) {
                        if (Integer.parseInt(couList1.getCou_shop_type()) == shopType.getGGtid()) {
                            couList1.setCou_drawstatus(1);
                        }
                    }
                }
                couLists.add(couList1);
            }
        }
        return JSON.toJSONString(couLists);
    }
}
