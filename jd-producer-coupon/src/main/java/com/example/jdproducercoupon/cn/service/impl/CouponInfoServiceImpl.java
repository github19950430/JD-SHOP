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
import com.example.jdproducercoupon.cn.mapper.CouponInfoDao;
import com.example.jdproducercoupon.cn.mapper.JdShopTypeDao;
import com.example.jdproducercoupon.cn.pojo.CouList;
import com.example.jdproducercoupon.cn.pojo.JdShopType;
import com.example.jdproducercoupon.cn.service.CouponInfoService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 〈一句话功能简述〉<br>
     * 获取所有优惠券信息
     *
     * @return
     * @author //TODO RanHaoHao
     * @date 2018/12/21 10:59
     */
    @Override
    public String getAllCoupon() throws ParseException {
        CouList couList = new CouList();
        List<CouList> couListList = couponInfoDao.select(couList);
        List<CouList> couLists = new ArrayList<>();
        for (CouList coulist : couListList) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long startTime = dateFormat.parse(coulist.getCou_starttime()).getTime();
            long passTime = dateFormat.parse(coulist.getCou_passtime()).getTime();
            long nowTime = System.currentTimeMillis();
            if (nowTime > startTime && nowTime < passTime) {
                Example jdShopTypeExample = new Example(JdShopType.class);
                jdShopTypeExample.createCriteria()
                        .andEqualTo("typeid", coulist.getCou_shop_type());
                coulist.setCou_shop_type(jdShopTypeDao.selectOneByExample(jdShopTypeExample).getTypename());

                couLists.add(coulist);
            }
        }
        return JSON.toJSONString(couLists);
    }
}
