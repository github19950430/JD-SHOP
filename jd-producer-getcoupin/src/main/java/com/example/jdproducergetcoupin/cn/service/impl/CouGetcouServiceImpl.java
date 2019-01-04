package com.example.jdproducergetcoupin.cn.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.jdproducergetcoupin.cn.dao.CouGetcouDao;
import com.example.jdproducergetcoupin.cn.dao.CouListDao;
import com.example.jdproducergetcoupin.cn.entity.CouGetcou;
import com.example.jdproducergetcoupin.cn.entity.CouList;
import com.example.jdproducergetcoupin.cn.service.CouGetcouService;
import com.example.jdproducergetcoupin.cn.util.MakeCouponId;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * (CouGetcou)表服务实现类
 *
 * @author makejava
 * @since 2018-12-24 15:14:24
 */
@Service
public class CouGetcouServiceImpl implements CouGetcouService {

    @Resource
    private CouListDao couListDao;

    @Resource
    private CouGetcouDao couGetcouDao;

    @Override
    public synchronized String getCoupon(Integer ownId, Integer couId) {
        Example couListExample = new Example(CouList.class);
        couListExample.createCriteria()
                .andEqualTo("cou_id", couId);
        Example couGetCouExample = new Example(CouGetcou.class);
        couGetCouExample.createCriteria()
                .andEqualTo("cou_lid", couId);
        if (couListDao.selectOneByExample(couListExample) == null) {
            return JSON.toJSONString("优惠券不存在");
        } else if (couGetcouDao.selectCountByExample(couGetCouExample) == couListDao.selectOneByExample(couListExample).getCou_getamount()){
            try {
                CouGetcou couGetcou = new CouGetcou();
                couGetcou.setCou_ownid(ownId);
                couGetcou.setCou_lid(couId);
                if (couGetcouDao.selectOne(couGetcou) != null) {
                    throw new Exception();
                }
            } catch (Exception e) {
                return JSON.toJSONString("您已领取，请勿重复领取");
            }
            CouList couList = couListDao.selectOneByExample(couListExample);
            if (couList.getCou_amount() > couList.getCou_getamount()) {
                CouGetcou couGetcou = new CouGetcou();
                couGetcou.setCou_ownid(ownId);
                couGetcou.setCou_lid(couId);
                couGetcou.setCou_cid(MakeCouponId.makeCouponID());
                couGetcou.setCou_status(0);
                couGetcouDao.insertSelective(couGetcou);
                couList.setCou_getamount(couList.getCou_getamount() + 1);
                if (couListDao.updateByPrimaryKeySelective(couList) > 0) {
                    return JSON.toJSONString("领取成功");
                } else {
                    return JSON.toJSONString("领取失败，请稍后重试");
                }
            } else {
                return JSON.toJSONString("优惠券领完了");
            }
        } else {
            return JSON.toJSONString("内部数据错误，请联系客服");
        }
    }
}