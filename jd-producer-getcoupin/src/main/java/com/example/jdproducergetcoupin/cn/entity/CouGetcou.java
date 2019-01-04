package com.example.jdproducergetcoupin.cn.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "cou_getcou")
public class CouGetcou{

    @Id
    private Integer cou_gid;
    private Integer cou_lid;
    private String cou_cid;
    private Integer cou_ownid;
    private Integer cou_status;
    private String cou_1;
    private String cou_2;
    private String cou_3;

}