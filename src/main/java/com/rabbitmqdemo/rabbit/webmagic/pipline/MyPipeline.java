package com.rabbitmqdemo.rabbit.webmagic.pipline;

import com.rabbitmqdemo.rabbit.webmagic.dao.FictionDao;
import com.rabbitmqdemo.rabbit.webmagic.entity.ChapterEntity;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author fsh
 * @date 2021/1/27.
 */
@Component
public class MyPipeline implements Pipeline {

    @Resource
    RestHighLevelClient restHighLevelClient;

    @Resource
    FictionDao fictionDao;

    @Override
    public void process(ResultItems resultItems, Task task) {

        ChapterEntity chapterEntity = new ChapterEntity();
        chapterEntity.setTitle(resultItems.get("title").toString());
        chapterEntity.setArt(resultItems.get("act").toString().replace("&nbsp;", " ")
                .replace("<br>", "")
                .replace("</div>", "")
                .replace("<div id=\"content\">", ""));
        chapterEntity.setArtName(resultItems.get("artName").toString());
        chapterEntity.setType(resultItems.get("type").toString());
        try {
            fictionDao.insert(chapterEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
