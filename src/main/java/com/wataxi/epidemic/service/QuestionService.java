package com.wataxi.epidemic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wataxi.epidemic.entity.Question;
import com.wataxi.epidemic.model.out.QuestionOut;

/**
 * @author yh200
 */
public interface QuestionService extends IService<Question> {
    Integer getLastId();
    boolean deleteQuestion(Integer id);
    QuestionOut getQuestionById(Integer id);
}
