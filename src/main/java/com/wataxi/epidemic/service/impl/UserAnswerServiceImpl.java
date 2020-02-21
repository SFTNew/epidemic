package com.wataxi.epidemic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wataxi.epidemic.entity.UserAnswer;
import com.wataxi.epidemic.mapper.UserAnswerMapper;
import com.wataxi.epidemic.service.UserAnswerService;
import org.springframework.stereotype.Service;

/**
 * @author yh200
 */
@Service
public class UserAnswerServiceImpl extends ServiceImpl<UserAnswerMapper, UserAnswer> implements UserAnswerService {
    @Override
    public Integer getLastId() {
        UserAnswer as = this.getOne(new QueryWrapper<UserAnswer>().orderByDesc("id"));
        return as == null?1:as.getId()+1;
    }
}
