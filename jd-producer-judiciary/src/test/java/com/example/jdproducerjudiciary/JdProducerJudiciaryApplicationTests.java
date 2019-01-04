package com.example.jdproducerjudiciary;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdProducerJudiciaryApplicationTests {

    public static void main(String[] args) {
          /*  String str = "1000000";
        boolean contains = str.contains(",");
        System.out.println(contains);
        String[] split = str.split(",");
        System.out.println(split[0]);
        System.out.println(str.length());



        //******
        str = new StringBuilder(str).reverse().toString();     //先将字符串颠倒顺序
        String str2 = "";
        for(int i=0;i<str.length();i++){
            if(i*3+3>str.length()){
                str2 += str.substring(i*3, str.length());
                break;
            }
            str2 += str.substring(i*3, i*3+3)+",";
        }
        if(str2.endsWith(",")){
            str2 = str2.substring(0, str2.length()-1);
        }
        //最后再将顺序反转过来
        String string = new StringBuilder(str2).reverse().toString();

        System.out.println(string);

        long time = System.currentTimeMillis();  //时间戳 毫秒
        System.out.println(time);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date()));
        System.out.println("********************");



        String asd = "1000";

        String[] splitone = asd.split(",");
        String one = "";
        for (String x:splitone) {
            one = one + x;
        }
        System.out.println(one);*/



         /* String a = "{\"alipay_trade_refund_response\":{\"code\":\"10000\",\"msg\":\"Success\",\"buyer_logon_id\":\"udp***@sandbox.com\",\"buyer_user_id\":\"2088102176752405\",\"fund_change\":\"Y\",\"gmt_refund_pay\":\"2018-12-29 10:01:02\",\"out_trade_no\":\"20181229095744490\",\"refund_fee\":\"100000.00\",\"send_back_fee\":\"0.00\",\"trade_no\":\"2018122922001452400500390744\"},\"sign\":\"snriqP1FPQ8CBUrNmDjWiNzCP2QXO/R/JVfa7CwrSC50x5LNSfO0LSCaGjweYL26EVp9tuu8NHLk5lOEqdGtGypi1xSTEqgJwBkeAcExDvgZCPNpGVogMKbN1HwlBOgDG0evZnRh89PwrwM7U6QP09xrivpBz4IiZnmh4H9uPw8R/1N1nLkMDM1wazy7+Vj5amim9UuWDRtjo2N23SiWDxzXPNj9wf1IqAsPg8xkbTLSsxtfGU/zdXVkDkjETrHtYvhR6CJlTTVvBspx9S7dco+Qs/ELhmo/TROUbr6WtUl+6lh/9Ru5KnFJzGLa3lK/xebyCXLqZw8rkllpJ60zSA==\"}";

        JSONObject jsonObject = JSON.parseObject(a);
        System.out.println(jsonObject.get("alipay_trade_refund_response"));
        String response = jsonObject.get("alipay_trade_refund_response").toString();
        JSONObject jsonObject1 = JSON.parseObject(response);
        System.out.println(jsonObject1.get("msg"));*/
      /*   String a =  1 + 1  +"";
        System.out.println(a);*/


    }

}

