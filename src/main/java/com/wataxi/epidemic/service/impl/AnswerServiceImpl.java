package com.wataxi.epidemic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wataxi.epidemic.entity.Answer;
import com.wataxi.epidemic.mapper.AnswerMapper;
import com.wataxi.epidemic.service.AnswerService;
import org.springframework.stereotype.Service;

/**
 * @author yh200
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {
    @Override
    public Integer getLastId() {
        Answer id = this.getOne(new QueryWrapper<Answer>().orderByDesc("id"));
        return id ==null?1:id.getId()+1;
    }
}
