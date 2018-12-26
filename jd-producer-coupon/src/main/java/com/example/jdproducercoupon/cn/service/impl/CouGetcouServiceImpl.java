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
import com.example.jdproducercoupon.cn.mapper.CouGetcouDao;
import com.example.jdproducercoupon.cn.pojo.CouGetcou;
import com.example.jdproducercoupon.cn.service.CouGetcouService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public String selSingleHasCoupon(Integer ownid) {
        CouGetcou couGetcou = new CouGetcou();
        couGetcou.setCou_ownid(ownid);
        return JSON.toJSONString(couGetcouDao.select(couGetcou));
    }
}
