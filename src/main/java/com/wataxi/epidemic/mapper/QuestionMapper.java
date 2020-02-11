package com.wataxi.epidemic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wataxi.epidemic.entity.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yh200
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}
