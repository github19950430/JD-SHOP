package com.example.jdproducercoupon.cn.pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;

@Getter
@Setter
@ToString
public class JdShopType {

  @Id
  private Integer typeid;
  private Integer fathertypeid;
  private String typename;
}
