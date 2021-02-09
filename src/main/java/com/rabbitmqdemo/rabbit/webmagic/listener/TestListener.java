package com.rabbitmqdemo.rabbit.webmagic.listener;

import org.springframework.data.redis.core.StringRedisTemplate;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

import javax.annotation.Resource;


public class TestListener implements SpiderListener {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void onSuccess(Request request) {

    }

    @Override
    public void onError(Request request) {
        stringRedisTemplate.opsForList();
    }
}
