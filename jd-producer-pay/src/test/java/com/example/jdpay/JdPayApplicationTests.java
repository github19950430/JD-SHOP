package com.example.jdpay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdPayApplicationTests {
    Date date = new Date();
    @Test
    public void contextLoads() {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String out_trade_no = sdf.format(date);
            System.out.println(out_trade_no);

    }

    public static void main(String[] args) {

        /*boolean equals = 3000 == 3000.0;
        System.out.println(equals);
        double v = Double.parseDouble("3000.01");
        System.out.println(v);*/
        long time = System.currentTimeMillis();  //时间戳 毫秒
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(time);

        Date date = new Date(time);
        String format = df.format(date);
        System.out.println(format);
    }

}

