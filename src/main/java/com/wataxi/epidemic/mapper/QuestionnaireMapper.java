package com.wataxi.epidemic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wataxi.epidemic.entity.Questionnaire;
import com.wataxi.epidemic.model.out.QuestionOut;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yh200
 */
public interface QuestionnaireMapper extends BaseMapper<Questionnaire> {
     List<QuestionOut> getQuestionsByQnId(@Param("id") Integer id);
}
