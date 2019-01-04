package com.example.jdproducerjudiciary.service;

import com.example.jdproducerjudiciary.entity.Judiciary;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * (Judiciary)表服务接口
 *
 * @author makejava
 * @since 2018-12-20 21:50:35
 */
public interface JudiciaryService {

    /**
     * 通过ID查询单条数据   根据司法商品ID 查询商品保证金
     *
     * @param judId 主键
     * @return 实例对象
     */
    String queryById(Integer judId);

    /**
     * 查询多条数据 导到solr
     *
     * @return 对象列表
     */
    void queryAll() throws IOException, SolrServerException;

    /**
     * 定时任务查询结束时间修改商品状态为已结束
     */
    void updateStatus() throws Exception;

    /**
     * 定时任务查询开始时间修改商品状态为拍卖中
     */
    void updateStatusAd() throws Exception;
    /**
     * 根据ID 查询拍卖商品名称
     *
     */
    String queryjudname(Integer judid);

    /**
     * 新增数据
     *
     * @param judiciary 实例对象
     * @return 实例对象
     */
    Judiciary insert(Judiciary judiciary);

    /**
     * 修改数据 加价接口
     *
     * @return 实例对象
     */
    String update(Integer userid,Integer judid);
    /**
     * 点击设置提醒 对提醒人数加一
     */
    String updateRemind(Integer judid);

}