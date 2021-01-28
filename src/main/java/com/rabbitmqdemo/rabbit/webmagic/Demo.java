package com.rabbitmqdemo.rabbit.webmagic;

import lombok.Data;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.example.GithubRepo;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author fsh
 * @date 2021/1/27.
 */
/*@HelpUrl("https://github.com/\\w+")*/
@TargetUrl("http://www.biqujia.com/book/0/132/\\w+.html")
@Data
public class Demo {

    @ExtractBy("//div[@class=bookname]/h1/text()")
    private String title;

    @ExtractBy("//div[@id=content]/text()")
    private String art;


}

