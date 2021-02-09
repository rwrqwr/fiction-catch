package com.rabbitmqdemo.rabbit.webmagic.scheduler;

import com.rabbitmqdemo.rabbit.webmagic.entity.enums.RedisType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fsh
 * @date 2021/2/2.
 */
@Component
public class TestScheduler extends DuplicateRemovedScheduler implements DuplicateRemover {



    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void pushWhenNoDuplicate(Request request, Task task) {

        stringRedisTemplate.opsForSet().add(getSetName(task),request.getUrl());
        stringRedisTemplate.opsForList().rightPush(getQueueName(task), request.getUrl());
        logger.info(request.getUrl() + "加入redis");
    }

    @Override
    public Request poll(Task task) {

        String url = stringRedisTemplate.opsForList().leftPop(getQueueName(task));

        return new Request(url);
    }

    @Override
    public boolean isDuplicate(Request request, Task task) {
        return Objects.requireNonNull(stringRedisTemplate.opsForSet().isMember(getSetName(task), request.getUrl())) ;
    }

    @Override
    public void resetDuplicateCheck(Task task) {
        stringRedisTemplate.delete(getSetName(task));
    }

    @Override
    public int getTotalRequestsCount(Task task) {
        return Objects.requireNonNull(stringRedisTemplate.opsForSet().size(getSetName(task))).intValue();
    }

    private String getQueueName(Task task){
        return RedisType.QUEUE.concat(task.getUUID());
    }
    private String getSetName(Task task){
        return RedisType.SET.concat(task.getUUID());
    }
}
