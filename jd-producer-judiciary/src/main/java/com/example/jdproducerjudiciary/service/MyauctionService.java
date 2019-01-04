package com.example.jdproducerjudiciary.service;

import com.example.jdproducerjudiciary.entity.Myauction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Myauction)表服务接口
 *
 * @author makejava
 * @since 2018-12-28 16:25:30
 */
public interface MyauctionService {

    /**
     * 通过ID查询单条数据
     *
     * @param myauctionId 主键
     * @return 实例对象
     */
    Myauction queryById(Integer myauctionId);

    /**
     * 查询多条数据   0 我的拍品 1 我的获拍
     *
     * @return 对象列表
     */
    String queryAllByLimit(Integer userid,Integer state);

    /**
     * 新增数据
     *
     * @param myauction 实例对象
     * @return 实例对象
     */
    Myauction insert(Myauction myauction);

    /**
     * 修改数据
     *
     * @param myauction 实例对象
     * @return 实例对象
     */
    Myauction update(Myauction myauction);

    /**
     * 通过主键删除数据
     *
     * @param myauctionId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer myauctionId);

}