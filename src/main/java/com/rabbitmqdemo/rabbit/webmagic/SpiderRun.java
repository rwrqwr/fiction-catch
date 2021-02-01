package com.rabbitmqdemo.rabbit.webmagic;

import com.rabbitmqdemo.rabbit.webmagic.entity.ChapterEntity;
import com.rabbitmqdemo.rabbit.webmagic.pageProcess.TestPagePro;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
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

    @Resource
    MyPipeline myPipeline;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000),new MyPipelineDataBase(), ChapterEntity.class)
                .addUrl("http://www.biqujia.com/book/0/132/").thread(10).run();
    }


    //todo 中断后需从头开始
    public void run() {
        /*OOSpider.create(Site.me().setSleepTime(1000),myPipelineDataBase, ChapterEntity.class,ChapterEntity.class)
                .addUrl("http://www.biqujia.com/book/0/132/").thread(10).run();*/
        Spider.create(new TestPagePro()).addUrl("http://www.biqujia.com/search.php?q=%25&p=1")
                .addPipeline(myPipeline).thread(10).run();
    }


}
