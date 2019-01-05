package com.example.jdconsumerjudiciary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdConsumerJudiciaryApplicationTests {

    public static void main(String[] args) {

        /*String a = "100000000";
        String b = "200,000,000";
        String[] split = a.split(",");
        String[] split1 = b.split(",");
        String aa = "";
        for (String x:split) {
        aa = aa + x;
        }
        System.out.println(Integer.parseInt(aa));


        String bb = "";
        for (String x:split1) {
        bb = bb + x;
        }
        System.out.println(Integer.parseInt(bb));


        String replace = a.replace(",", "");
        System.out.println("***"+replace);

        String str = "15153.00";
        String substring = str.substring(0, str.indexOf("."));
        System.out.println(substring);*/
        Long a = 100l;
        int b = 111;
        System.out.println(a + b);
    }
}

