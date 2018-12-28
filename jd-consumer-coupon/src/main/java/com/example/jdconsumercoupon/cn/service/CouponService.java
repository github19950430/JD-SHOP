/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CouponService
 * Author:   RanHaoHao
 * Date:     2018/12/28 21:27
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
package com.example.jdconsumercoupon.cn.service;

import com.example.jdconsumercoupon.cn.service.impl.CouponServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author RanHaoHao
 * @create 2018/12/28
 * @since 1.0.0
 */
@FeignClient(value = "jd-producer-coupon", fallback = CouponServiceImpl.class)
public interface CouponService {
    @GetMapping(value = "/getAll")
    String getAllCoupon(@RequestParam(value = "ownid") Integer ownid);

    @GetMapping(value = "/selcouponinfo")
    String selSingleHasCoupon(@RequestParam(value = "ownid") Integer ownid);

    @GetMapping(value = "/usecoupon")
    String useCoupon(@RequestParam(value = "couShopType") String couShopType, @RequestParam(value = "ownid") Integer ownid);

}
