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
package com.example.jdproducercouponinfo.cn.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.jdproducercouponinfo.cn.mapper.CouponInfoDao;
import com.example.jdproducercouponinfo.cn.pojo.CouList;
import com.example.jdproducercouponinfo.cn.service.CouponInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        return JSON.toJSONString(couponInfoDao.selectAll());
    }
}
