package com.example.jdproducerjudiciary.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.jdproducerjudiciary.dao.JudiciaryDao;
import com.example.jdproducerjudiciary.entity.Judiciary;
import com.example.jdproducerjudiciary.entity.Myauction;
import com.example.jdproducerjudiciary.dao.MyauctionDao;
import com.example.jdproducerjudiciary.service.MyauctionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Myauction)表服务实现类
 *
 * @author makejava
 * @since 2018-12-28 16:25:30
 */
@Service
public class MyauctionServiceImpl implements MyauctionService {
    @Resource
    private MyauctionDao myauctionDao;
    @Resource
    private JudiciaryDao judiciaryDao;


    /**
     * 通过ID查询单条数据
     *
     * @param myauctionId 主键
     * @return 实例对象
     */
    @Override
    public Myauction queryById(Integer myauctionId) {
        return this.myauctionDao.queryById(myauctionId);
    }

    /**
     * 查询多条数据   0我的拍品  和  1  我的获拍
     *
     * @return 对象列表
     */
    @Override
    public String queryAllByLimit(Integer userid,Integer state) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Myauction> myauctions = this.myauctionDao.queryAllByLimit(userid, state);
        if (state == 0) {
            for (Myauction x : myauctions) {
                Judiciary queryname = judiciaryDao.queryname(x.getMyauctionShopid());//商品名称
                Judiciary querycurrent = judiciaryDao.querycurrent(x.getMyauctionShopid());//结束时间 当前价格 图片
                String judTimeout = querycurrent.getJudTimeout();//结束时间
                long timeout = Long.parseLong(judTimeout);//结束时间
                long timenew = System.currentTimeMillis();//当前时间戳
                if (timeout > timenew){
                    x.setMystate("竞拍中");
                }else {
                    x.setMystate("已结束");
                }
                x.setMyjudname(queryname.getJudTradename());//商品名称
                x.setMyprice(querycurrent.getJudCurrentprice());//将当前价格
                String myauctionTime = x.getMyauctionTime();//下拍时间 时间戳
                Date date = new Date(Long.parseLong(myauctionTime));
                String format = simpleDateFormat.format(date);
                x.setMyauctionTime(format);//转后的时间
                System.out.println();
                x.setMypicture(querycurrent.getJudPicture());//图片
            }
        }else if (state == 1) {
            for (Myauction x : myauctions) {

                Judiciary queryname = judiciaryDao.queryname(x.getMyauctionShopid());
                Judiciary querycurrent = judiciaryDao.querycurrent(x.getMyauctionShopid());//结束时间 当前价格 图片
                x.setMyjudname(queryname.getJudTradename());//商品名称

                x.setMyprice(querycurrent.getJudCurrentprice());//将当前价格
                String myauctionTime = x.getMyauctionTime();//下拍时间 时间戳
                Date date = new Date(Long.parseLong(myauctionTime));
                String format = simpleDateFormat.format(date);
                x.setMyauctionTime(format);//转后的时间
                x.setMypicture(querycurrent.getJudPicture());//图片
                x.setMystate("已获拍");
            }
        }
        return JSON.toJSONString(myauctions);
    }

    /**
     * 新增数据
     *
     * @param myauction 实例对象
     * @return 实例对象
     */
    @Override
    public Myauction insert(Myauction myauction) {
        this.myauctionDao.insert(myauction);
        return myauction;
    }

    /**
     * 修改数据
     *
     * @param myauction 实例对象
     * @return 实例对象
     */
    @Override
    public Myauction update(Myauction myauction) {
        this.myauctionDao.update(myauction);
        return this.queryById(myauction.getMyauctionId());
    }

    /**
     * 通过主键删除数据
     *
     * @param myauctionId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer myauctionId) {
        return this.myauctionDao.deleteById(myauctionId) > 0;
    }
}