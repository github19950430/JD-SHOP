package com.example.jdproducerjudiciary.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.jdproducerjudiciary.entity.Bids;
import com.example.jdproducerjudiciary.dao.BidsDao;
import com.example.jdproducerjudiciary.service.BidsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Bids)表服务实现类
 *
 * @author makejava
 * @since 2018-12-20 21:04:21
 */
@Service
public class BidsServiceImpl implements BidsService {
    @Resource
    private BidsDao bidsDao;

    /**
     * 通过商品ID查询三条数据进行排序
     *
     * @param judid 主键
     * @return 实例对象
     */
    @Override
    public String queryById(Integer judid) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Bids> bids = this.bidsDao.queryById(judid);
        for (Bids x:bids) {
            String bidsTime = x.getBidsTime();
            long time = Long.parseLong(bidsTime);
            Date date = new Date(time);
            String format = simpleDateFormat.format(date);
            System.out.println("年月日=" + format);
            x.setBidsTime(format);
        }
        return JSON.toJSONString(bids);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Bids> queryAllByLimit(int offset, int limit) {
        return this.bidsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param bids 实例对象
     * @return 实例对象
     */
    @Override
    public Bids insert(Bids bids) {
        this.bidsDao.insert(bids);
        return bids;
    }

    /**
     * 修改数据
     *
     * @param bids 实例对象
     * @return 实例对象
     */
    @Override
    public String update(Bids bids) {
        this.bidsDao.update(bids);
        return this.queryById(bids.getBidsId());
    }

    /**
     * 通过主键删除数据
     *
     * @param bidsId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer bidsId) {
        return this.bidsDao.deleteById(bidsId) > 0;
    }
}