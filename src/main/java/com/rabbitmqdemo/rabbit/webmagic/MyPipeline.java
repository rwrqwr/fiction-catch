package com.rabbitmqdemo.rabbit.webmagic;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author fsh
 * @date 2021/1/27.
 */
@Component
public class MyPipeline implements Pipeline {

    @Resource
    RestHighLevelClient restHighLevelClient;

    @Override
    public void process(ResultItems resultItems, Task task) {

        HashMap<String,Object> map = new HashMap<>();
        map.put("title",resultItems.get("title"));
        map.put("act",resultItems.get("art"));
        map.put("artName", resultItems.get("actName").toString().replace("&nbsp;", " ")
                .replace("<br>", "")
                .replace("</div>", "")
                .replace("<div id=\"content\">", ""));
        IndexRequest indexRequest = new IndexRequest("content").source(resultItems);
        try {
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
