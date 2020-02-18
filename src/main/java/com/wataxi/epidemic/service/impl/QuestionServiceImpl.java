package com.wataxi.epidemic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wataxi.epidemic.conmmon.R;
import com.wataxi.epidemic.entity.Question;
import com.wataxi.epidemic.mapper.QuestionMapper;
import com.wataxi.epidemic.service.AnswerService;
import com.wataxi.epidemic.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yh200
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>  implements QuestionService {
    @Autowired
    private AnswerService answerService;

    @Override
    public Integer getLastId() {
        Question qs = this.getOne(new QueryWrapper<Question>().orderByDesc("id"));
        return qs == null?1:qs.getId()+1;
    }

    @Transactional
    @Override
    public boolean deleteQuestion(Integer id) {
        Question byId = this.getById(id);
        if(null == byId){
            return false;
        }
        byId.setSign(1);
        this.updateById(byId);
        answerService.deleteByQuestionId(id);
        return true;
    }
}
