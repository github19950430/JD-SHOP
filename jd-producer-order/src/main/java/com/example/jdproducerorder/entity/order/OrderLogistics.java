package com.example.jdproducerorder.entity.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (OrderLogistics)实体类
 *
 * @author 阿飞！
 * @since 2018-12-26 10:13:58
 */
@Data
@ApiModel
public class OrderLogistics implements Serializable {
    private static final long serialVersionUID = -74585644139525501L;
    @ApiModelProperty(hidden = true)
    private Integer logisticsId;
    @ApiModelProperty(required = true, value = "//物流公司的编号")
    private String logisticsCompany;
    @ApiModelProperty(required = true, value = "//运单号")
    private String logisticsNo;
    @ApiModelProperty(required = true, value = "//订单号")
    private String orderNo;

    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date sendTime;
    @ApiModelProperty(hidden = true)
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date takeTime;//收货时间

}