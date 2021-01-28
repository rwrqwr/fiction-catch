package com.rabbitmqdemo.rabbit.webmagic;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author fsh
 * @date 2021/1/27.
 */
public class MyPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("res+++++========"+resultItems.toString());
    }
}
