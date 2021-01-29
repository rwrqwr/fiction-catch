package com.rabbitmqdemo.rabbit.webmagic;

import com.rabbitmqdemo.rabbit.webmagic.entity.ChapterEntity;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import javax.annotation.Resource;

/**
 * @author fsh
 * @date 2021/1/27.
 */

@Component
public class SpiderRun {

    @Resource
    MyPipelineDataBase myPipelineDataBase;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000),new MyPipelineDataBase(), ChapterEntity.class)
                .addUrl("http://www.biqujia.com/book/0/132/").thread(10).run();
    }

    public void run() {
        OOSpider.create(Site.me().setSleepTime(1000),myPipelineDataBase, ChapterEntity.class)
                .addUrl("http://www.biqujia.com/book/0/132/").thread(10).run();
    }


}
