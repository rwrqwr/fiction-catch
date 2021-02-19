package com.rabbitmqdemo.rabbit.webmagic.listener;

import com.rabbitmqdemo.rabbit.webmagic.dao.LogInfDao;
import com.rabbitmqdemo.rabbit.webmagic.entity.LogInf;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

import javax.annotation.Resource;

@Component
public class TestListener implements SpiderListener {

    @Resource
    LogInfDao logInfDao;

    @Override
    public void onSuccess(Request request) {

    }

    @Override
    public void onError(Request request) {
        LogInf logInf = new LogInf();
        logInf.setUrl(request.getUrl());
        logInfDao.insert(logInf);
    }
}
