package com.example.jdproducerjudiciary.dao;

import com.example.jdproducerjudiciary.entity.Myauction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Myauction)表数据库访问层
 *
 * @author makejava
 * @since 2018-12-28 16:25:30
 */
@Mapper
public interface MyauctionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param myauctionId 主键
     * @return 实例对象
     */
    Myauction queryById(Integer myauctionId);

    /**
     * 查询指定行数据      查询 0 我的竞拍  和 1 我的获拍
     * @return 对象列表
     */
    List<Myauction> queryAllByLimit(@Param("userid") Integer userid,@Param("state")Integer state);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param myauction 实例对象
     * @return 对象列表
     */
    List<Myauction> queryAll(Myauction myauction);

    /**
     * 新增数据
     *
     * @param myauction 实例对象
     * @return 影响行数
     */
    int insert(Myauction myauction);

    /**
     * 修改数据
     *
     * @param myauction 实例对象
     * @return 影响行数
     */
    int update(Myauction myauction);

    /**
     * 通过主键删除数据
     *
     * @param myauctionId 主键
     * @return 影响行数
     */
    int deleteById(Integer myauctionId);

}