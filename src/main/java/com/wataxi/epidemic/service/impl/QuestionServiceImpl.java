package com.wataxi.epidemic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wataxi.epidemic.entity.Question;
import com.wataxi.epidemic.mapper.QuestionMapper;
import com.wataxi.epidemic.service.QuestionService;
import org.springframework.stereotype.Service;

/**
 * @author yh200
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>  implements QuestionService {
    @Override
    public Integer getLastId() {
        Question qs = this.getOne(new QueryWrapper<Question>().orderByDesc("id"));
        return qs == null?1:qs.getId()+1;
    }
}
