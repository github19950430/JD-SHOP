package com.example.jdproducerjudiciary.solr.impl;

import com.alibaba.fastjson.JSON;
import com.example.jdproducerjudiciary.dao.CityDao;
import com.example.jdproducerjudiciary.entity.City;
import com.example.jdproducerjudiciary.entity.Judiciary;
import com.example.jdproducerjudiciary.selpojo.ProductSearch;
import com.example.jdproducerjudiciary.solr.SearchProductDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchProductDaoImpl implements SearchProductDao {
    @Resource
    private CityDao cityDao;

    private static final String solrUrl="http://188.131.133.118:8983/solr/judiciary-core";//司法core

    //创建solrClient同时指定超时时间，不指定走默认配置
    private static HttpSolrClient client=new HttpSolrClient.Builder(solrUrl).build();

    @Override
    public List<Judiciary> searchProduct(ProductSearch productSearch) throws Exception {
        //设置查询对象
        SolrQuery solrQuery = new SolrQuery();
        //params 支持多个fq查询的对象  执行查询时 执行params这个对象
        ModifiableSolrParams params = new ModifiableSolrParams();
        //设置关键字
        if ("".equals(productSearch.getQueryString()) || null == productSearch.getQueryString()){
            System.out.println("没有关键字"+ productSearch.getQueryString());
            solrQuery.setQuery("*:*");
        }else {
            solrQuery.setQuery(productSearch.getQueryString());
        }
        //设置默认搜索域
        solrQuery.set("df", "product_keywords");
        //设置过滤条件  分类
        if(null != productSearch.getCatalog_name() && !"".equals(productSearch.getCatalog_name())){
            System.out.println("有分类" + productSearch.getCatalog_name());
            solrQuery.addFilterQuery("judcategory:" + productSearch.getCatalog_name());
        }
        //所在地
        if (null != productSearch.getSite() && !"".equals(productSearch.getSite())){
            System.out.println("有所在地" + productSearch.getSite());
            solrQuery.addFilterQuery("judAreacity:" + productSearch.getSite());
        }
        //状态
        if (null != productSearch.getState() && !"".equals(productSearch.getState())){
            System.out.println("有状态" + productSearch.getState());
            solrQuery.addFilterQuery("judStatus:" + productSearch.getState());
        }/*else {
            solrQuery.addFilterQuery("judStatus:1");// 1拍卖中 2预告中 3已结束 4已暂缓 5已中止 6已撤回
        }*/
        //阶段
        if (null != productSearch.getStage() && !"".equals(productSearch.getStage())){
            System.out.println("有阶段" + productSearch.getStage());
            solrQuery.addFilterQuery("judFrequency:" + productSearch.getStage());
        }/*else {
            solrQuery.addFilterQuery("judFrequency:1");// 1一拍 2二拍 3变卖
        }*/
        //对价格进行过滤
        if(null != productSearch.getPrice() && !"".equals(productSearch.getPrice())){
            System.out.println("有价格" + productSearch.getPrice());
            String[] p = productSearch.getPrice().split("-");
            solrQuery.addFilterQuery("judCurrentprice:[" + p[0] + " TO " + p[1] + "]");
        }
        // 价格排序
        if ("1".equals(productSearch.getSort())) {
            System.out.println("排序1");
            solrQuery.addSort("judCurrentprice", ORDER.desc);
        } else {
            //默认进这个
            System.out.println("排序0");
            solrQuery.addSort("judCurrentprice", ORDER.asc);
        }
        // 分页
        if ("".equals(productSearch.getPageSize()) || productSearch.getPageSize() == null){
            solrQuery.setStart(0);//第几行开始
        }else {
            solrQuery.setStart(productSearch.getPageSize());//第几行开始
        }
        solrQuery.setRows(12);//一页显示多少行
        // 执行查询
        params.add(solrQuery);
        QueryResponse response = client.query(params);
        // 文档结果集
        SolrDocumentList docs = response.getResults();
        System.out.println("****"+JSON.toJSONString(docs) + "----" +docs);
        List<Judiciary> jud = new ArrayList<>();
        for (SolrDocument doc : docs) {
            Judiciary judiciary = new Judiciary();
            judiciary.setJudId(Integer.parseInt((String) doc.get("id")));
            judiciary.setJudCategory(Integer.parseInt((String) doc.get("judcategory")));

            City judAreacity = cityDao.queryById(Integer.parseInt((String) doc.get("judAreacity")));
            String cityName = judAreacity.getCityName();
            judiciary.setJudAreacity(cityName);

            judiciary.setJudStatus(Integer.parseInt((String) doc.get("judStatus")));
            judiciary.setJudFrequency(Integer.parseInt((String) doc.get("judFrequency")));
            judiciary.setJudTradename((String) doc.get("judTradename"));
            judiciary.setJudSite((String) doc.get("judSite"));
            judiciary.setJudLongitude((String) doc.get("judLongitude"));
            judiciary.setJudDimensionality((String) doc.get("judDimensionality"));
            judiciary.setJudAuctiontime((String) doc.get("judAuctiontime"));
            judiciary.setJudTimeout((String) doc.get("judTimeout"));
            judiciary.setJudWillnum(Integer.parseInt((String) doc.get("judWillnum")));
            judiciary.setJudOnlooker(Integer.parseInt((String) doc.get("judOnlooker")));
            judiciary.setJudCurrentprice((String) doc.get("judCurrentprice"));
            judiciary.setJudStartingprice((String) doc.get("judStartingprice"));
            judiciary.setJudValuation((String) doc.get("judValuation"));
            judiciary.setJudBidincrement((String) doc.get("judBidincrement"));
            judiciary.setJudCashdeposit((String) doc.get("judCashdeposit"));
            judiciary.setJudRemind(Integer.parseInt((String) doc.get("judRemind")));
            judiciary.setJudPicture((String) doc.get("judPicture"));
            judiciary.setJudDisposeunit((String) doc.get("judDisposeunit"));
            judiciary.setJudLinkman((String) doc.get("judLinkman"));
            jud.add(judiciary);
            System.out.println("地址"+doc.get("judSite"));
        }
        return jud;
    }
}
