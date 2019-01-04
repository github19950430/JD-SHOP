package com.example.jdproducerorder.dao;

import com.example.jdproducerorder.entity.order.OrderLogistics;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (OrderLogistics)表数据库访问层
 *
 * @author 阿飞！
 * @since 2018-12-26 10:13:58
 */
public interface OrderLogisticsDao {

    /**
     * 通过运单号单条数据
     *
     * @param logisticsNo 运单号
     * @return 实例对象
     */
    OrderLogistics queryBylogisticsNo(String logisticsNo);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OrderLogistics> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param orderLogistics 实例对象
     * @return 对象列表
     */
    List<OrderLogistics> queryAll(OrderLogistics orderLogistics);

    /**
     * 新增数据
     *
     * @param orderLogistics 实例对象
     * @return 影响行数
     */
    int insert(OrderLogistics orderLogistics);

    /**
     * 修改数据
     *
     * @param orderLogistics 实例对象
     * @return 影响行数
     */
    int update(OrderLogistics orderLogistics);

    /**
     * 通过主键删除数据
     *
     * @param logisticsId 主键
     * @return 影响行数
     */
    int deleteById(Integer logisticsId);

}