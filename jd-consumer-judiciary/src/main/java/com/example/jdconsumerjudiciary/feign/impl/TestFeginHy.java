package com.example.jdconsumerjudiciary.feign.impl;

import com.example.jdconsumerjudiciary.feign.TestFeign;
import org.springframework.stereotype.Component;

@Component
public class TestFeginHy implements TestFeign {

    @Override
    public String aa() {
        return "0000";
    }
}
