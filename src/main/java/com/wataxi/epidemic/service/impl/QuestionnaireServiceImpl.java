package com.wataxi.epidemic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wataxi.epidemic.conmmon.ex.EpidemicException;
import com.wataxi.epidemic.entity.Answer;
import com.wataxi.epidemic.entity.Question;
import com.wataxi.epidemic.entity.Questionnaire;
import com.wataxi.epidemic.mapper.QuestionnaireMapper;
import com.wataxi.epidemic.model.in.QuestionAndAnswerIn;
import com.wataxi.epidemic.model.out.AnswerOut;
import com.wataxi.epidemic.model.out.QuestionOut;
import com.wataxi.epidemic.service.AnswerService;
import com.wataxi.epidemic.service.QuestionService;
import com.wataxi.epidemic.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author yh200
 */
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire> implements QuestionnaireService {


    @Autowired
    private QuestionService qService;
    @Autowired
    private AnswerService aService;

    @Override
    public Integer getLastId() {
        Questionnaire qs = this.getOne(new QueryWrapper<Questionnaire>().orderByDesc("id"));
        return qs == null?1:qs.getId()+1;
    }

    @Transactional
    @Override
    public void addQuestionAndAnswer(Integer id, QuestionAndAnswerIn qa) throws EpidemicException {
        Questionnaire byId = this.getById(id);
        if(null == byId){
            throw new EpidemicException("");
        }
        //生成问题
        Question question = new Question();
        question.setQnid(id);
        question.setId(qService.getLastId());
        question.setStatus(0);
        question.setSign(0);
        question.setSort(1);
        question.setType(qa.getType());
        question.setTitle(qa.getQuestion());
        question.setCreateTime(new Date());
        qService.save(question);
        //生成问题的答案
        if(qa.getType() != 2){
            List<AnswerOut> as = qa.getAnswers();
            if(null != as &&  as.size()>0) {
                for (AnswerOut aw : as) {
                    Answer answer = new Answer();
                    answer.setId(aService.getLastId());
                    answer.setContent(aw.getContent());
                    answer.setQid(question.getId());
                    answer.setSign(0);
                    answer.setSort(aw.getSort());
                    answer.setStatus(0);
                    aService.save(answer);
                }
            }
        }
    }

    @Override
    public List<QuestionOut> getQuestionsByQnId(Integer id) {
        return this.baseMapper.getQuestionsByQnId(id);
    }
}
