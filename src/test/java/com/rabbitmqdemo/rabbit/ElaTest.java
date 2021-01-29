package com.rabbitmqdemo.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmqdemo.rabbit.RabbitApplication;
import com.rabbitmqdemo.rabbit.webmagic.SpiderRun;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fsh
 * @date 2020/8/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElaTest {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Resource
    SpiderRun spiderRun;

    @Test
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
            this.restHighLevelClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testAdd(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", "20200129");
        map.put("name", "测试2");
        map.put("age", 22);
        try {
            IndexRequest indexRequest = new IndexRequest("content").source(map);
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            System.out.println(indexResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRunner(){
        spiderRun.run();
    }

    @Test
    public void testIndex() throws IOException {
        SearchRequest sq = new SearchRequest().indices("content");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        sq.source(searchSourceBuilder);
        searchSourceBuilder.query(QueryBuilders.matchQuery("title","4278"));
        SearchResponse sr = restHighLevelClient.search(sq, RequestOptions.DEFAULT);
        SearchHit[] hits = sr.getHits().getHits();
        for (SearchHit hit : hits) {
            JSONObject jsonObject = JSONObject.parseObject(hit.getSourceAsString());
            String content = jsonObject.get("content").toString();
            System.out.println(content);
            System.out.println(jsonObject.get("title").toString());
        }
    }

    @Test
    public void testDeleteIndex(){
        DeleteIndexRequest dq = new DeleteIndexRequest("content");
        try {
            AcknowledgedResponse delete = restHighLevelClient.indices().delete(dq, RequestOptions.DEFAULT);
            System.out.println(delete.isAcknowledged());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
