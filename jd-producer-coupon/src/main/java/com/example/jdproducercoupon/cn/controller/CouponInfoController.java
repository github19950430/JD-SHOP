/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CouponInfoController
 * Author:   RanHaoHao
 * Date:     2018/12/21 11:06
 * Description: 优惠券信息API
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
package com.example.jdproducercoupon.cn.controller;

import com.example.jdproducercoupon.cn.service.CouGetcouService;
import com.example.jdproducercoupon.cn.service.CouponInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈优惠券信息API〉
 *
 * @author RanHaoHao
 * @create 2018/12/21 11:06
 * @since 1.0.0
 */
@RestController
public class CouponInfoController {
    @Resource
    private CouponInfoService couponInfoService;

    @Resource
    private CouGetcouService couGetcouService;

    @GetMapping(value = "/getAll")
    public String getAllCoupon(@RequestParam Integer ownid) throws ParseException {
        return couponInfoService.getAllCoupon(ownid);
    }

    @GetMapping(value = "/selcouponinfo")
    public String selSingleHasCoupon (@RequestParam Integer ownid) {
        return couGetcouService.selSingleHasCoupon(ownid);
    }

    @GetMapping(value = "/usecoupon", produces = "text/json;charset=utf-8")
    public String useCoupon(@RequestParam String couShopType, HttpServletRequest request) {
        Integer userid = (Integer) request.getAttribute("userid");
        System.out.println("userid = " + userid);
        return couGetcouService.orderUseCoupon(couShopType, userid);
    }
}
