package com.rabbitmqdemo.rabbit.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author fsh
 * @date 2020/8/19.
 */
public class Elasticsearch {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    public void search(){
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("47.106.74.12", 9200, "http")));
        SearchRequest searchRequest = new SearchRequest().indices("gxft");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        MatchPhrasePrefixQueryBuilder mppqb = QueryBuilders.matchPhrasePrefixQuery("name", "测试");
        sourceBuilder.query(mppqb);
        try {
            SearchResponse sr = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            String result = sr.toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
