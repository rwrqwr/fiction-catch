package com.rabbitmqdemo.rabbit.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author fsh
 * @date 2021/1/27.
 */
public class Demo2 implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
//        page.putField("title",);

        // 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(page.getHtml().links().regex("(http://www.biqujia.com/book/0/132/\\w+\\.html)").all());

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new Demo2())
                .addUrl("http://www.biqujia.com/book/0/132/")
                .addPipeline(new MyPipeline())
                .thread(5)
                .run();
    }

}
