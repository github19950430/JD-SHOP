package com.example.jdproducergetcoupin.cn.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "cou_list")
public class CouList {
  @Id
  private Integer cou_id;
  private String cou_name;
  private String cou_starttime;
  private String cou_passtime;
  private Integer cou_type;
  private Integer cou_quota;
  private Integer cou_sale;
  private Integer cou_discount;
  private Integer cou_amount;
  private Integer cou_getamount;
}
