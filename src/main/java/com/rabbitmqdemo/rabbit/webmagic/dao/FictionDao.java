package com.rabbitmqdemo.rabbit.webmagic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbitmqdemo.rabbit.webmagic.entity.ChapterEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fsh
 * @date 2021/2/5.
 */
@Mapper
public interface FictionDao extends BaseMapper<ChapterEntity> {
}
