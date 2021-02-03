package com.rabbitmqdemo.rabbit.webmagic;

import com.rabbitmqdemo.rabbit.webmagic.entity.ChapterEntity;
import com.rabbitmqdemo.rabbit.webmagic.pageProcess.TestPagePro;
import com.rabbitmqdemo.rabbit.webmagic.scheduler.TestScheduler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import javax.annotation.Resource;

/**
 * @author fsh
 * @date 2021/1/27.
 */

@Controller
@RequestMapping("/spider")
public class SpiderRun {

    @Resource
    MyPipelineDataBase myPipelineDataBase;

    @Resource
    MyPipeline myPipeline;

    @Resource
    TestScheduler testScheduler;

    public static void main(String[] args) {


    }


    @GetMapping("run")
    public void run() {

        /*OOSpider.create(Site.me().setSleepTime(1000),myPipelineDataBase, ChapterEntity.class,ChapterEntity.class)
                .addUrl("http://www.biqujia.com/book/0/132/").thread(10).run();*/
        Spider.create(new TestPagePro())
                .addPipeline(myPipeline)
                .addUrl("http://www.biqujia.com/search.php?q=%25&p=1")
                .setScheduler(testScheduler)
                .thread(10).run();


    }

}
