package com.example.jdconsumerjudiciary.feign.impl;

import com.example.jdconsumerjudiciary.entity.Cashdeposit;
import com.example.jdconsumerjudiciary.feign.JudSelFegin;
import com.example.jdconsumerjudiciary.selpojo.ProductSearch;
import org.springframework.stereotype.Component;

@Component
public class JudSelImplHy implements JudSelFegin {
    @Override
    public String selectOne(ProductSearch productSearch) throws Exception{

        return "0000";

    }

    @Override
    public String seldetails(Integer judid,Integer userid) throws Exception {
        return "0000";
    }

    @Override
    public String selcash(Integer id) {
        return "0000";
    }

    @Override
    public String seljudname(Integer judid) {
        return "0000";
    }


    @Override
    public String selectOnebao(Cashdeposit cashdeposit) {

        return "0000";
    }

    @Override
    public String addcash(Cashdeposit cashdeposit) {
        return "0000";
    }

    @Override
    public String selcashbao(Integer userid) {
        return "0000";
    }

    @Override
    public String updataprice(Integer userid, Integer judid) {
        return "0000";
    }

    @Override
    public String updateRemind(Integer judid) {
        return "0000";
    }

    @Override
    public String selbids(Integer judid) {
        return "0000";
    }

    @Override
    public String selmyauction(Integer userid, Integer state) {
        return "0000";
    }
}
