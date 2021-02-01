package com.rabbitmqdemo.rabbit.webmagic.entity;

import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @author fsh
 * @date 2021/2/1.
 */
@TargetUrl("http://www.biqujia.com/book/\\w+\\w")
@HelpUrl("http://www.biqujia.com/book/search.php?q=%25&p=2")
public class LinkEntity {
}
