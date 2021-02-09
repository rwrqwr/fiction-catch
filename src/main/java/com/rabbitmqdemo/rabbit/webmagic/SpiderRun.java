package com.rabbitmqdemo.rabbit.webmagic;

import com.rabbitmqdemo.rabbit.webmagic.listener.TestListener;
import com.rabbitmqdemo.rabbit.webmagic.pageProcess.TestPagePro;
import com.rabbitmqdemo.rabbit.webmagic.pipline.MyPipeline;
import com.rabbitmqdemo.rabbit.webmagic.pipline.MyPipelineDataBase;
import com.rabbitmqdemo.rabbit.webmagic.scheduler.TestScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;
import us.codecraft.webmagic.monitor.SpiderMonitor;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

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

    @Resource
    TestListener testListener;

    public static void main(String[] args) {


    }


    @GetMapping("run")
    public void run() {

        /*OOSpider.create(Site.me().setSleepTime(1000),myPipelineDataBase, ChapterEntity.class,ChapterEntity.class)
                .addUrl("http://www.biqujia.com/book/0/132/").thread(10).run();*/
        List<SpiderListener> spiderListenerList = new LinkedList<>();
        spiderListenerList.add(testListener);
        Spider.create(new TestPagePro())
                .addPipeline(myPipeline)
                .addUrl("http://www.biqujia.com/search.php?q=%25&p=1")
                .setScheduler(testScheduler)
                .setSpiderListeners(spiderListenerList)
                .thread(10).run();

    }

}
