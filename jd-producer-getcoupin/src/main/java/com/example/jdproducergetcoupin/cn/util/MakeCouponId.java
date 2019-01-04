/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MakeCouponId
 * Author:   RanHaoHao
 * Date:     2018/12/19 18:07
 * Description: 生成唯一优惠券ID
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 * 　　　　　　　 ┏┓       ┏┓+ +
 * 　　　　　　　┏┛┻━━━━━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　 ┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 █████━█████  ┃+
 * 　　　　　　　┃　　　　　　 ┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　 ┃ + +
 * 　　　　　　　┗━━┓　　　 ┏━┛
 * 　　　　　　　　　┃　　  ┃ + + + +
 * 　　　　　　　　　┃　　　┃　Code is far away from     bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　         神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　 ┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━━━┳┓┏┛ + + + +
 * 　　　　　　　　　 ┃┫┫　 ┃┫┫
 * 　　　　　　　　　 ┗┻┛　 ┗┻┛+ + + + *
 */
package com.example.jdproducergetcoupin.cn.util;

import java.util.UUID;

/**
 * 〈一句话功能简述〉<br> 
 * 〈生成唯一优惠券ID〉
 *
 * @author RanHaoHao
 * @create 2018/12/19
 * @since 1.0.0
 */
public class MakeCouponId {
    
    /**
     *〈一句话功能简述〉<br> 
     * 生成优惠券ID的静态方法
     * @author //TODO RanHaoHao
     * @date  2018/12/24 16:10
     * @return string 返回ID
     */
    public static String makeCouponID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
