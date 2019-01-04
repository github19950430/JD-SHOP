package com.example.jdproducerjudiciary.solr.impl;

import com.alibaba.fastjson.JSON;
import com.example.jdproducerjudiciary.entity.Judiciary;
import com.example.jdproducerjudiciary.selpojo.ProductSearch;
import com.example.jdproducerjudiciary.solr.Seldetails;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeldetailsImpl implements Seldetails {
    private static final String solrUrl="http://188.131.133.118:8983/solr/judiciary-core";//司法core

    //创建solrClient同时指定超时时间，不指定走默认配置
    private static HttpSolrClient client=new HttpSolrClient.Builder(solrUrl).build();

    @Override
    public List<Judiciary> searchProduct(Integer judid) throws Exception {
        //设置查询对象
        SolrQuery solrQuery = new SolrQuery();
        //设置关键字
        solrQuery.setQuery("id:" + judid);
        //设置默认搜索域
        solrQuery.set("df", "product_keywords");

        QueryResponse response = client.query(solrQuery);
        // 文档结果集
        SolrDocumentList docs = response.getResults();
        System.out.println("****"+JSON.toJSONString(docs) + "----" +docs);
        List<Judiciary> jud = new ArrayList<>();
        for (SolrDocument doc : docs) {
            Judiciary judiciary = new Judiciary();
            judiciary.setJudId(Integer.parseInt((String) doc.get("id")));
            judiciary.setJudCategory(Integer.parseInt((String) doc.get("judcategory")));
            judiciary.setJudAreacity((String) doc.get("judAreacity"));
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
