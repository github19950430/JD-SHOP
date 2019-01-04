package com.example.jdproducerjudiciary.dao;

import com.example.jdproducerjudiciary.entity.Bids;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Bids)表数据库访问层
 *
 * @author makejava
 * @since 2018-12-20 21:04:20
 */
@Mapper
public interface BidsDao {

    /**
     * 通过商品ID查询三条数据进行排序
     *
     * @param judid 商品ID
     * @return 实例对象
     */
    List<Bids> queryById(Integer judid);

    /**
     * 查看商品出价次数
     * @param judid
     * @return
     */
    int querylength(Integer judid);
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Bids> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param bids 实例对象
     * @return 对象列表
     */
    List<Bids> queryAllbid(Bids bids);

    /**
     * 新增数据
     *
     * @param bids 实例对象
     * @return 影响行数
     */
    int insert(Bids bids);

    /**
     * 修改数据
     *
     * @param bids 实例对象
     * @return 影响行数
     */
    int update(Bids bids);

    /**
     * 通过主键删除数据
     *
     * @param bidsId 主键
     * @return 影响行数
     */
    int deleteById(Integer bidsId);

}