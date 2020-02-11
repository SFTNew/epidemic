package com.wataxi.epidemic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wataxi.epidemic.entity.Questionnaire;
import com.wataxi.epidemic.mapper.QuestionnaireMapper;
import com.wataxi.epidemic.service.QuestionnaireService;
import org.springframework.stereotype.Service;

/**
 * @author yh200
 */
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire> implements QuestionnaireService {
    @Override
    public Integer getLastId() {
        Questionnaire qs = this.getOne(new QueryWrapper<Questionnaire>().orderByDesc("id"));
        return qs == null?1:qs.getId()+1;
    }
}
