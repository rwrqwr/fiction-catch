package com.rabbitmqdemo.rabbit.webmagic.pipline;

import com.rabbitmqdemo.rabbit.webmagic.entity.ChapterEntity;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fsh
 * @date 2021/1/27.
 */
@Component
public class MyPipelineDataBase implements PageModelPipeline<ChapterEntity> {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Override
    public void process(ChapterEntity o, Task task) {

        //将 前端空格（&nbsp;） 替换成空格和换行符号
        o.setArt(o.getArt().replace("&nbsp;", " ")
                .replace("<br>","")
                .replace("</div>","")
                .replace("<div id=\"content\">",""));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("update", new Date());
        map.put("title", o.getTitle());
        map.put("content", o.getArt());
        map.put("artName",o.getArtName());
        try {
            IndexRequest indexRequest = new IndexRequest("content").source(map);
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            //todo: es插入返回错误
            if (indexResponse.getShardInfo().getFailed() == 1){
                throw new RuntimeException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
