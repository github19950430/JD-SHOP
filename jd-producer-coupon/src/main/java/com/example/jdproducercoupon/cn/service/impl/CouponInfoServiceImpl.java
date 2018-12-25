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
    //@RabbitListener(queues = RabbitConfig.queue_MinusInventory)
    public void upCoupon(Integer cou_id) {
        CouList couList = new CouList();
        couList.setCou_id(cou_id);
        couList = couponInfoDao.selectOne(couList);
        couList.setCou_getamount(couList.getCou_getamount() + 1);
        couponInfoDao.updateByPrimaryKeySelective(couList);
    }
}
