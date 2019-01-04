package com.example.jdproducerjudiciary.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.jdproducerjudiciary.dao.CashdepositDao;
import com.example.jdproducerjudiciary.dao.JudiciaryDao;
import com.example.jdproducerjudiciary.entity.Cashdeposit;
import com.example.jdproducerjudiciary.entity.Judiciary;
import com.example.jdproducerjudiciary.service.CashdepositService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Cashdeposit)表服务实现类
 *
 * @author makejava
 * @since 2018-12-26 11:55:43
 */
@Service
public class CashdepositServiceImpl implements CashdepositService {
    @Resource
    private CashdepositDao cashdepositDao;
    @Resource
    private JudiciaryDao judiciaryDao;

    Date date = new Date();
    /**
     * 通过ID查询单条数据  查看保证金  参数有问题或者没有的话返回null
     *
     * @return 实例对象
     */
    @Override
    public String queryById(Cashdeposit cashdeposit) {
        System.out.println("userid"+cashdeposit.getCashUser() + "typeid" + cashdeposit.getCashType() + "shopid" + cashdeposit.getCashShopid());
        try{
            if (null == cashdeposit.getCashUser() || null == cashdeposit.getCashType() || null == cashdeposit.getCashShopid())
                return null;
            Cashdeposit cashdeposit1 = this.cashdepositDao.queryById(cashdeposit);
            if (null == cashdeposit1)
                return null;
            return cashdeposit1.getCashPrice();
        }catch (Exception e){

        }
        return null;
    }

    /**
     * 查看保证金 根据用户id
     * @param cashUser
     * @return
     */
    @Override
    public String queryByuserid(Integer cashUser) {
        List<Cashdeposit> cashdeposits = cashdepositDao.queryByuserid(cashUser);
        for (Cashdeposit x:cashdeposits) {
            Judiciary queryname = judiciaryDao.queryname(x.getCashShopid());
            x.setJudname(queryname.getJudTradename());
        }
        return JSON.toJSONString(cashdeposits);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<Cashdeposit> queryAllByLimit() {
        return this.cashdepositDao.queryAllByLimit();
    }

    /**
     * 新增数据
     *
     * @param cashdeposit 实例对象  --------添加保证金表  缴纳保证金
     * @return 实例对象
     */
    @Override
    public String insert(Cashdeposit cashdeposit) {


        Judiciary judiciary = judiciaryDao.queryById(cashdeposit.getCashShopid());
        String judCashdeposit = judiciary.getJudCashdeposit();
        cashdeposit.setCashPrice(judCashdeposit);//保证金
        cashdeposit.setCashType(1);

        int insert = this.cashdepositDao.insert(cashdeposit);
        if (insert > 0) {
            int i = judiciaryDao.updateWillNum(cashdeposit.getCashShopid());//成功缴纳保证金后 修改该商品的报名人数
            if (i > 0)
            return "缴纳成功!保证金：" + cashdeposit.getCashPrice();
        }
        return "缴纳失败!";

    }

    /**
     * 修改数据
     *
     * @param cashdeposit 实例对象
     * @return 实例对象
     */
    @Override
    public String update(Cashdeposit cashdeposit) {
        this.cashdepositDao.update(cashdeposit);
        return this.queryById(cashdeposit);
    }
}