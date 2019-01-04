package com.example.jdproducerjudiciary.solr.impl;

import com.alibaba.fastjson.JSON;
import com.example.jdproducerjudiciary.entity.Judiciary;
import com.example.jdproducerjudiciary.selpojo.ProductSearch;
import com.example.jdproducerjudiciary.solr.SelStatus;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SelStatusImpl implements SelStatus {

    private static final String solrUrl="http://188.131.133.118:8983/solr/judiciary-core";//司法core

    //创建solrClient同时指定超时时间，不指定走默认配置
    private static HttpSolrClient client=new HttpSolrClient.Builder(solrUrl).build();

    @Override
    public List<Judiciary> searchProduct() throws Exception {
        //设置查询对象
        SolrQuery solrQuery = new SolrQuery();
        //设置关键字
        solrQuery.setQuery("judStatus:1");// 1拍卖中 2预告中 3已结束 4已暂缓 5已中止 6已撤回
        //设置默认搜索域
        solrQuery.set("df", "judStatus");
        // 执行查询
        QueryResponse response = client.query(solrQuery);
        // 文档结果集
        SolrDocumentList docs = response.getResults();
//        System.out.println("****"+JSON.toJSONString(docs) + "----" +docs);
        List<Judiciary> jud = new ArrayList<>();
        for (SolrDocument doc : docs) {
            Judiciary judiciary = new Judiciary();
            judiciary.setJudId(Integer.parseInt((String) doc.get("id")));
            judiciary.setJudTimeout((String) doc.get("judTimeout"));
            jud.add(judiciary);
        }
        return jud;
    }
}
