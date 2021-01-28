package com.rabbitmqdemo.rabbit.webmagic;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

/**
 * @author fsh
 * @date 2021/1/27.
 */
public class SpiderRun {

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000)
                , new MyPipelineDataBase(), Demo.class)
                .addUrl("http://www.biqujia.com/book/0/132/").thread(10).run();
    }

}
