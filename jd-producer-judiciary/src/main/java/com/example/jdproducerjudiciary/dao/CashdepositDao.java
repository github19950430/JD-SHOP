package com.example.jdproducerjudiciary.dao;

import com.example.jdproducerjudiciary.entity.Cashdeposit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Cashdeposit)表数据库访问层
 *
 * @author makejava
 * @since 2018-12-26 11:55:43
 */
@Mapper
public interface CashdepositDao {

    /**
     * 通过ID查询单条数据   查看保证金   ----------
     *
     * @return 实例对象
     */
    Cashdeposit queryById(Cashdeposit cashdeposit);
    /**
     *
     * 根据用户ID查询保证金
     */
    List<Cashdeposit> queryByuserid(Integer cashUser);
    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<Cashdeposit> queryAllByLimit();


    /**
     * 通过实体作为筛选条件查询
     *
     * @param cashdeposit 实例对象
     * @return 对象列表
     */
    List<Cashdeposit> queryAll(Cashdeposit cashdeposit);

    /**
     * 新增数据 ----------      添加保证金表  缴纳保证金
     *
     * @param cashdeposit 实例对象
     * @return 影响行数
     */
    int insert(Cashdeposit cashdeposit);

    /**
     * 修改数据
     *
     * @param cashdeposit 实例对象
     * @return 影响行数
     */
    int update(Cashdeposit cashdeposit);

    /**
     * 通过主键删除数据
     *
     * @param cashId 主键
     * @return 影响行数
     */
    int deleteById(Integer cashId);

}