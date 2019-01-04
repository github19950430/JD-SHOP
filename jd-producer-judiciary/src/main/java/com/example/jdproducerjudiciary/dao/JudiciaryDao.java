package com.example.jdproducerjudiciary.dao;

import com.example.jdproducerjudiciary.entity.Judiciary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Judiciary)表数据库访问层
 *
 * @author makejava
 * @since 2018-12-20 21:50:35
 */
@Mapper
public interface JudiciaryDao {

    /**
     * 通过ID查询单条数据      根据司法商品ID 查询商品保证金
     *
     * @param judId 主键
     * @return 实例对象
     */
    Judiciary queryById(Integer judId);


    /**
     * 查询当前价格 和 查询结束时间
     */
    Judiciary querycurrent(Integer judId);

    /**
     * 查询拍卖名称 根据ID
     */
    Judiciary queryname(Integer judId);
    /**
     * 查询多条数据 导到solr
     *
     * @return 对象列表
     */
    List<Judiciary> queryAlljud();


    /**
     * 新增数据  后台
     *
     * @param judiciary 实例对象
     * @return 影响行数
     */
    int insert(Judiciary judiciary);

    /**
     * 修改数据 通过主键修改数据 加价修改
     *
     * @return 影响行数
     */
    int update(@Param("judId") Integer judId, @Param("price") String price,@Param("time") String time);
    /**
     * 修改报名人数 加一
     */
    int updateWillNum(@Param("judId") Integer judId);
    /**
     * 点击设置提醒 修改提醒人数
     */
    int updateRemind(@Param("judId") Integer judId);

    /**
     * 定时任务修改 拍卖状态 修改为已结束
     * @param judId
     * @return
     */
    int updateStatus(@Param("judId") Integer judId);

    /**
     * 定时任务修改 拍卖状态 修改为拍卖中
     * @param judId
     * @return
     */
    int updateStatusAd(@Param("judId") Integer judId);
}