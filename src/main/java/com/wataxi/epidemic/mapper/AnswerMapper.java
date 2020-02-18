package com.wataxi.epidemic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wataxi.epidemic.entity.Answer;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yh200
 */
@Mapper
public interface AnswerMapper extends BaseMapper<Answer> {
    /**
     * 删除问题的答案
     * @param id 问题id
     */
    void deleteByQuestionId(Integer id);
}
