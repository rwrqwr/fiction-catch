package com.rabbitmqdemo.rabbit.webmagic.pageProcess;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fsh
 * @date 2021/2/1.
 */
public class TestPagePro implements PageProcessor {

    Pattern[] linkPattern = {Pattern.compile("http://www.biqujia.com/search.php\\?q=%25&p=\\d"),
            Pattern.compile("http://www.biqujia.com/book/\\w+/\\w+/\\w+.html"),
            Pattern.compile("http://www.biqujia.com/book/\\w+/\\w+/")};
//    Pattern pagePatten = Pattern.compile("");
    String pagePatten = "http://www.biqujia.com/book/\\d/\\d/\\d.html";

    @Override
    public void process(Page page) {

        if (page.getUrl().regex(pagePatten).match()){
            page.putField("title",page.getHtml().xpath("//div[@class=bookname]/h1/text()"));
            page.putField("act",page.getHtml().xpath("//div[@id=content]"));
            page.putField("artName",page.getHtml().xpath("//div[@class=con_top]/a[3]/text()"));
        }else {
            List<String> all = page.getHtml().links().all();
            for (String s : all) {
                for (Pattern pattern : linkPattern) {
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.find()) {
                        page.addTargetRequest(matcher.group());
                        page.setSkip(true);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public Site getSite() {
        return new Site();
    }
}
