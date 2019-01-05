package com.example.jdproducerjudiciary.entity;

import java.io.Serializable;

/**
 * (City)实体类
 *
 * @author makejava
 * @since 2019-01-05 10:51:38
 */
public class City implements Serializable {
    private static final long serialVersionUID = 353217957335006754L;
    //城市分类ID
    private Integer cityId;
    //城市名称
    private String cityName;


    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}