package com.example.jdproducerjudiciary.service;

import com.example.jdproducerjudiciary.entity.Cashdeposit;

import java.util.List;

/**
 * (Cashdeposit)表服务接口
 *
 * @author makejava
 * @since 2018-12-26 11:55:43
 */
public interface CashdepositService {

    /**
     * 通过ID查询单条数据 查看保证金  参数有问题或者没有的话返回null
     *
     * @return 实例对象
     */
    String queryById(Cashdeposit cashdeposit);

    /**
     * 根据用户ID查询保证金
     * @param cashUser
     * @return
     */
    String queryByuserid(Integer cashUser);
    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Cashdeposit> queryAllByLimit();

    /**
     * 新增数据
     *
     * @param cashdeposit 实例对象  -------添加保证金表  缴纳保证金
     * @return 实例对象
     */
    String insert(Cashdeposit cashdeposit);

    /**
     * 修改数据
     *
     * @param cashdeposit 实例对象
     * @return 实例对象
     */
    String update(Cashdeposit cashdeposit);

}