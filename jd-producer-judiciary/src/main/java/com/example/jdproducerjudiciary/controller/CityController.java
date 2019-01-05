package com.example.jdproducerjudiciary.controller;

import com.example.jdproducerjudiciary.entity.City;
import com.example.jdproducerjudiciary.service.CityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (City)表控制层
 *
 * @author makejava
 * @since 2019-01-05 10:51:38
 */
@RestController
public class CityController {
    /**
     * 服务对象
     */
    @Resource
    private CityService cityService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public City selectOne(Integer id) {
        return this.cityService.queryById(id);
    }

}