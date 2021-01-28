package com.rabbitmqdemo.rabbit.webmagic;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fsh
 * @date 2021/1/27.
 */
@Component
public class MyPipelineDataBase implements PageModelPipeline<Demo> {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Override
    public void process(Demo o, Task task) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", "20190909");
        map.put("name", "测试");
        map.put("age", 22);
        try {
            IndexRequest indexRequest = new IndexRequest("content", "doc", map.get("id").toString()).source(map);
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            System.out.println(indexResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        restHighLevelClient.;

        System.out.println(o.getArt());
        //将 前端空格（&nbsp;） 和换行符号 替换成空格
        o.setArt(o.getArt().replace("&nbsp;", " ")
                .replace("<br>",""));
        System.out.println(o.getArt());
    }
}
