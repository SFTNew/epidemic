package com.wataxi.epidemic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wataxi.epidemic.entity.Question;
import com.wataxi.epidemic.model.out.QuestionOut;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yh200
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    QuestionOut getQuestionById(Integer id);
}
