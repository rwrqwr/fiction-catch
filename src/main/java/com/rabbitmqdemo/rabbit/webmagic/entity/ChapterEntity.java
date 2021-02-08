package com.rabbitmqdemo.rabbit.webmagic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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

import java.io.Serializable;

/**
 * @author fsh
 * @date 2021/1/27.
 */
@TargetUrl("http://www.biqujia.com/book/\\w+\\w+.html")
@HelpUrl("http://www.biqujia.com/book/")
@Data
@TableName("fiction")
public class ChapterEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @ExtractBy("//div[@class=bookname]/h1/text()")
    private String title;

    @ExtractBy("//div[@id=content]")
    private String art;

    @ExtractBy("//div[@class=con_top]/a[3]/text()")
    private String artName;


}

