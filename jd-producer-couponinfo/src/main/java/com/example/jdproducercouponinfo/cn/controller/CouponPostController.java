/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CouponPostController
 * Author:   RanHaoHao
 * Date:     2018/12/19 19:08
 * Description: 发放优惠券API
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
package com.example.jdproducercouponinfo.cn.controller;

import com.example.jdproducercouponinfo.cn.pojo.CouList;
import com.example.jdproducercouponinfo.cn.service.CouponPostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br> 
 * 〈发放优惠券API〉
 *
 * @author RanHaoHao
 * @create 2018/12/19
 * @since 1.0.0
 */
@RestController
public class CouponPostController {
    @Resource
    private CouponPostService couponPostService;

    @PostMapping(value = "/postcou", produces = "text/json;charset=utf-8")
    public String couponPost(CouList couList){
        System.out.println(couList.getCou_name());
        return couponPostService.addCoupon(couList);
    }
}
