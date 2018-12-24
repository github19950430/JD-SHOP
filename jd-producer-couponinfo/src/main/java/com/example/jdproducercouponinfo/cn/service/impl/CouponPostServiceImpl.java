/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CouponPostServiceImpl
 * Author:   RanHaoHao
 * Date:     2018/12/19 17:56
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
import com.example.jdproducercouponinfo.cn.mapper.CouponPostDao;
import com.example.jdproducercouponinfo.cn.pojo.CouList;
import com.example.jdproducercouponinfo.cn.service.CouponPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.jdproducercouponinfo.cn.util.DateUtil.isValidDate;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author RanHaoHao
 * @create 2018/12/19
 * @since 1.0.0
 */
@Service
public class CouponPostServiceImpl implements CouponPostService {
    @Resource
    private CouponPostDao couponPostDao;

    /**
     *〈一句话功能简述〉<br>
     * @deprecated 添加优惠券信息
     * @author //TODO RanHaoHao
     * @date  2018/12/19 17:55
     * @param couList 实体类对象
     * @return String 返回添加优惠券状态信息
     */
    @Override
    public String addCoupon(CouList couList) {
        //couList.setCou_cid(new MakeCouponId().makeCouponID().replace("-",""));
        //couList.setCou_stauts(0);
        String startTime;
        Date start;
        if (isValidDate(couList.getCou_starttime())){
            SimpleDateFormat startDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
            try {
                start = startDateFormat.parse(couList.getCou_starttime());
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                return JSON.toJSONString("优惠券开始时间格式错误");
            }
            startTime = startDateFormat.format(start);
            couList.setCou_starttime(startTime);
        } else {
            SimpleDateFormat startDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            startTime = startDateFormat.format(System.currentTimeMillis());
            couList.setCou_starttime(startTime);
        }
        SimpleDateFormat startDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            start = startDateFormat.parse(startTime);
        } catch (ParseException e) {
            return JSON.toJSONString("优惠券开始时间格式错误");
        }
        long stime = start.getTime();
        SimpleDateFormat passDateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        long time = Integer.parseInt(couList.getCou_passtime());
        couList.setCou_passtime(passDateFormat.format(stime + time * 24 * 60 * 60 * 1000));
        if(couponPostDao.insertSelective(couList) >= 0){
            return JSON.toJSONString("添加成功");
        } else {
            return JSON.toJSONString("添加失败");
        }
    }
}
