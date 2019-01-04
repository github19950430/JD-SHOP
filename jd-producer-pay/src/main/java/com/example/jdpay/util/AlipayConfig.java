package com.example.jdpay.util;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092100561247";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCnvfeR2EpivdrG58nGiYMwC3QhiKP4ZHSVLKJMPRMR72nYylPsQx48UZvdZFZNerYktqsqcdtIv2vVNJYDblvsb8WTP/Mb2VMzrkFN+s0smaIHe7ZFgj1nWi9c7tDmpoB2TKFEppc8JXKsAi/FUiUCvcoDdh6p6BBWotCM6oNJSAXlh8Bf3xWL1G/zg2LeZYb4bSroes0KzJ8r4wCpDAfyWC0UY7Bp9LNulazLsHzXFQq+GJb20L/mfthzMLXc/Hqppo78Ab0WcujhMsvP7ZEHoGminOIP/0fip23eCUwzHOZdsZD8tKnoXgLMOyDyW+jHb8Nsq65wMNQFFVvaIN25AgMBAAECggEAOjvZFujf6ie/LA6MHkDfKm7PglTNmA5H6wHz1kPduvxC4uBbAkrK1GYAlnue+ob/dP91rOaaGkdaSEpZShemEg87qEMrQx/PosF8KnZHhJRRjAeG39q3Noo4pyrBm1Fyh59S3WJ4XokhplWcAATklG8PEuSPAK1v/qPHgyRnL6kffqlthqUZbXcfWDZBCiKhgt4SMgKEKOcOS4yIRM0teTrvdsdsvUrFak8324o6QNNb1Y71xeLhHOuZew9LcVyMsdYTNhbcuIMmYV0kaIRMYWzPLEdT9HSJ2Wh95bLqMzkVLWBvljPrtdtoSPb+q37E/pHQQVUkzR+WLJQy5OB+AQKBgQD03FqQb4B9rzbWIM4LLsd+bLgURsmpVY0KE1KXH+EHaSe0ZTDg8os51Ixi7WIBkq2tTGokRdVr8sDw3FGJvqB8MUh6R0jruUYeyWZPQpPneFK8seUzDCfAdMutdpSvqCe7yPmPB+aRMHXPk5F3hAkPDFMJ6Kv8ejm4YzJSaBQ7JQKBgQCvX31XuW5vU5uoDYO7DqGAvh61PEh5Rd1i1rtlYVIK58oeHGXmGNAPggpYJktbGQs6axQ3aFVDZEvf9AcQvzbuGxTuw17zwaHR9qJLhVS/GUgWg7ozkCsUxjnS96Ye0Glet2arSb0nArf5M/14E0IaNf6sVpIrRjVcTjyyY8n+BQKBgQCHKVuUfvgeaKmMaW9THO9iCgFSsACBSIgiF6hvY1mEDbetDG5+vWO8+GbF9OM0mxLjplqwyxuUCtwWGIep0WAlLc8kMFVASXU7aVDgT4/1JaYGIuyx0PPgeJg1mE9a5yeHsNiQ63uKiYV+n6aconWdqN/tiFHnfFCAR8FvW6RPQQKBgHUe2J7VgCNpOvMo02kBhGpujiOLdvtG5Ws07UHvlNMjqaAiZwscVxeESVRFft4AA7en+4BldUvkl6ehWXadvRmXjItIfwlbr66BgDHfgZF+WN5watI03hCpscRrD85UcYqIAVEzldePwT30srqWYfcPSF3gEw5DfS+AMJkSe7vhAoGAR+I3Nc4othcCXiFES3F6R+JLvgdFr5rUfWpsD7Kf/8pr6rrAvxHyheZVzU3t1hUWy/jgizvOavzIztK2/eqMXgslymf/FJvgvQP+Nj0A+Wk71y1Y3wwK8VSn3ULZxPBG15ptnUdoePCW+BhPCD9z5woE53i2WSubElFsF7pZN3c=";
    //    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx6UnxtKrCyVZJlIqAtVbxUkaiL/UiEj31gdNEkGWJFDH1K3wecwn1Hu9/gz4nc97P6pXLp2QutisOUkGhfdh6UMUDqLyan+kmHMtsFAkPgjVka0UAq8opVLpW2kcmn4u7mqLRF9WcvN6Qrbi7dlaNIC2wfgg59v379T1Yrgy7ts+pGOIY0dBDNJRs8p6zRESTI7yasW9UQgKJLJkQYwZutTyV2XsebSkZkE25kgaj/x2CPzZMdS/gekqftIiLOhMwbqoEUFNxGfcZaVejSr+F7LzkwUEv1z7KqBrtJLnmAY7SxOhpL0Hn+gHUrdf6Nrk4/byhrEijwtfaunHVAmEgQIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://192.168.51.62:8888/notify_url";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://192.168.51.62:8889/order";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志路径 /usr/local/paylog
    public static String log_path = "D:\\paylog";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {

        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
