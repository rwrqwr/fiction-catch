package com.rabbitmqdemo.rabbit.webmagic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("log_inf")
public class LogInf {

    private String url;

    private Integer code;

    private String exception;

}
