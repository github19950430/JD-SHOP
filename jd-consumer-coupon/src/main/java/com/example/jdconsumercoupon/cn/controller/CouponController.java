/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CouponController
 * Author:   RanHaoHao
 * Date:     2018/12/28 21:42
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
package com.example.jdconsumercoupon.cn.controller;

import com.example.jdconsumercoupon.cn.service.CouponService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author RanHaoHao
 * @create 2018/12/28 21:42
 * @since 1.0.0
 */
@RestController
public class CouponController {

    @Resource
    private CouponService couponService;

    @GetMapping(value = "/feigngetall")
    public String getAllCoupon(Integer ownid) {
        return couponService.getAllCoupon(ownid);
    }

    @GetMapping(value = "/feignselcouponinfof")
    public String selSingleHasCoupon (Integer ownid) {
        return couponService.selSingleHasCoupon(ownid);
    }

    @GetMapping(value = "/feignusecouponf")
    public String useCoupon(String couShopType, Integer ownid) {
        return couponService.useCoupon(couShopType, ownid);
    }

}
