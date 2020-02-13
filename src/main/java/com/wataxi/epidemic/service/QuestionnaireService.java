package com.wataxi.epidemic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wataxi.epidemic.conmmon.ex.EpidemicException;
import com.wataxi.epidemic.entity.Questionnaire;
import com.wataxi.epidemic.model.in.QuestionAndAnswerIn;
import com.wataxi.epidemic.model.out.QuestionOut;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yh200
 */
public interface QuestionnaireService extends IService<Questionnaire> {
    public Integer getLastId();

    void addQuestionAndAnswer(Integer id, QuestionAndAnswerIn qa) throws EpidemicException;
    List<QuestionOut> getQuestionsByQnId(Integer id);
}
