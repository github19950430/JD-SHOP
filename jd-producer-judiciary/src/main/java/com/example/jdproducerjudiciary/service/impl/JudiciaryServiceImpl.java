package com.example.jdproducerjudiciary.service.impl;

import com.example.jdproducerjudiciary.dao.BidsDao;
import com.example.jdproducerjudiciary.dao.JudiciaryDao;
import com.example.jdproducerjudiciary.entity.Bids;
import com.example.jdproducerjudiciary.entity.Judiciary;
import com.example.jdproducerjudiciary.rabbitmqUitl.RabbitConfig;
import com.example.jdproducerjudiciary.selpojo.ProductSearch;
import com.example.jdproducerjudiciary.service.JudiciaryService;
import com.example.jdproducerjudiciary.solr.SearchProductDao;
import com.example.jdproducerjudiciary.solr.SelStatus;
import com.example.jdproducerjudiciary.solr.SelStatusAdvance;
import com.example.jdproducerjudiciary.uitl.RedisUtil;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Judiciary)表服务实现类
 *
 * @author makejava
 * @since 2018-12-20 21:50:35
 */
@Service
public class JudiciaryServiceImpl implements JudiciaryService {

    @Resource
    private JudiciaryDao judiciaryDao;
    @Resource
    private BidsDao bidsDao;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private SimpMessagingTemplate template;
    @Resource
    private SelStatus SelStatus;
    @Resource
    private SelStatusAdvance selStatusAdvance;
    Bids bids = new Bids();

    private static final String solrUrl="http://188.131.133.118:8983/solr/judiciary-core";//司法core
    //创建solrClient同时指定超时时间，不指定走默认配置
    private static HttpSolrClient client=new HttpSolrClient.Builder(solrUrl).build();

    /**
     * 通过ID查询单条数据 根据司法商品ID 查询商品保证金
     *
     * @param judId 主键
     * @return 实例对象
     */
    @Override
    public String queryById(Integer judId) {

        String judCashdeposit = "";
        try{
            Object cash = redisUtil.get("cash" + judId);
            if (cash != null){
                System.out.println("商品保证金=" + cash.toString());
                return cash.toString();
            }
            Judiciary judiciary = this.judiciaryDao.queryById(judId);
            judCashdeposit = judiciary.getJudCashdeposit(); //根据商品ID查询保证金
            //将保证金放redis里 失效时间两小时
            redisUtil.set("cash" + judId,judCashdeposit,60*60*2);

        }catch (NullPointerException e){

            return null;
        }
        return judCashdeposit;
    }

    /**
     * 查询多条数据 导到solr
     *
     * @return 对象列表
     */
    @Override
    @Scheduled(fixedRate = 5000) //5秒
    public void queryAll() throws IOException, SolrServerException {
        List<Judiciary> judiciaries = judiciaryDao.queryAlljud();
        for (Judiciary x:judiciaries) {
            SolrInputDocument document = new SolrInputDocument();
            //id int
            document.addField("id",x.getJudId());
            //拍品类别 int
            document.addField("judcategory",x.getJudCategory());
            //山西
            document.addField("judAreacity",x.getJudAreacity());
            //状态 int
            document.addField("judStatus",x.getJudStatus());
            //拍的状态 int
            document.addField("judFrequency",x.getJudFrequency());
            document.addField("judTradename",x.getJudTradename());
            document.addField("judSite",x.getJudSite());
            document.addField("judLongitude",x.getJudLongitude());
            document.addField("judDimensionality",x.getJudDimensionality());
            document.addField("judAuctiontime",x.getJudAuctiontime());
            document.addField("judTimeout",x.getJudTimeout());
            document.addField("judWillnum",x.getJudWillnum());
            document.addField("judOnlooker",x.getJudOnlooker());
            document.addField("judCurrentprice",x.getJudCurrentprice());
            document.addField("judStartingprice",x.getJudStartingprice());
            document.addField("judValuation",x.getJudValuation());
            document.addField("judBidincrement",x.getJudBidincrement());
            document.addField("judCashdeposit",x.getJudCashdeposit());
            document.addField("judRemind",x.getJudRemind());
            document.addField("judPicture",x.getJudPicture());
            document.addField("judDisposeunit",x.getJudDisposeunit());
            document.addField("judLinkman",x.getJudLinkman());
            client.add(document);
        }
        client.commit();
        System.out.println("solr successfully");
    }

    @Override
    @Scheduled(fixedRate = 10000)//10秒查询一次
    public void updateStatus() throws Exception {
        long time = System.currentTimeMillis();  //时间戳 毫秒
        List<Judiciary> judiciaries = SelStatus.searchProduct();
        for (Judiciary x:judiciaries) {
            if (Long.parseLong(x.getJudTimeout()) < time){
                int i = judiciaryDao.updateStatus(x.getJudId());
                System.out.println(i);
            }
        }
        System.out.println("结束修改成功");
    }

    @Override
    @Scheduled(fixedRate = 10000)//10秒查询一次
    public void updateStatusAd() throws Exception {
        long time = System.currentTimeMillis();  //时间戳 毫秒
        List<Judiciary> judiciaries = selStatusAdvance.searchProduct();
        for (Judiciary x:judiciaries) {
            if (Long.parseLong(x.getJudAuctiontime()) < time){
                int i = judiciaryDao.updateStatusAd(x.getJudId());
                System.out.println(i);
            }
        }
        System.out.println("拍卖中修改成功");
    }


    //查询拍卖名称 根据ID
    @Override
    public String queryjudname(Integer judid) {
        Judiciary queryname = judiciaryDao.queryname(judid);
        return queryname.getJudTradename();
    }


    /**
     * 新增数据
     *
     * @param judiciary 实例对象
     * @return 实例对象
     */
    @Override
    public Judiciary insert(Judiciary judiciary) {
        this.judiciaryDao.insert(judiciary);
        return judiciary;
    }

    /**
     * 修改数据  点击加价  抢购   //用户ID 和 商品ID
     *
     * @return 实例对象
     */
    @Override
    public String update(Integer userid,Integer judid) {

        Judiciary querycurrent = judiciaryDao.querycurrent(judid);

        String judCurrentprice = querycurrent.getJudCurrentprice();//当前价格
        String price = judCurrentprice.replace(",", "");
        int iiprice = Integer.parseInt(price);//当前价格
        String Bidincrement = querycurrent.getJudBidincrement();//加价幅度
        String replace = Bidincrement.replace(",", "");
        int iireplace = Integer.parseInt(replace);

        String str = iiprice + iireplace + "";

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

        String judTimeout = querycurrent.getJudTimeout(); //拍品结束时间

        Long timeout = Long.parseLong(judTimeout) + 300000;

        int update = this.judiciaryDao.update(judid, string,timeout + "");//修改商品当前价格 和结束拍卖时间 +5分钟
        if (update > 0){
            bids.setBidsUserid(userid);
            bids.setBidsGoods(judid);
            bids.setBidsPrice(string);

            long time = System.currentTimeMillis();  //时间戳 毫秒

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(time);
            String format = df.format(date);

            bids.setBidsTime(time + "");
            int insert = bidsDao.insert(bids);//添加出价表
            if (insert > 0){
                System.out.println("123123====" + judid);
                // 查询商品出价次数
                int querylength = bidsDao.querylength(judid);
//                System.out.println("次数=" + querylength);
                String message = "{\"userid\":\"" + userid + "\"," + "\"price\":\"" + string + "\"," + "\"time\":\"" + format + "\"," + "\"leng\":\"" + querylength + "\"}";
                this.template.convertAndSendToUser(judid + "","/queue/getResponse",message);
                //使用webscok 推送页面
            }

            return "恭喜您出价成功!当前价格为：" + string;
        }
        return "出价失败!";
    }

    /**
     * 点击设置提醒 对提醒人数加一
     */
    @Override
    public String updateRemind(Integer judid) {
        int i = judiciaryDao.updateRemind(judid);
        if (i > 0)
            return "设置提醒成功!";
        return "设置提醒成功!请重试!";
    }
}