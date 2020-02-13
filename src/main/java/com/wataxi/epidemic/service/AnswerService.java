package com.wataxi.epidemic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wataxi.epidemic.entity.Answer;

public interface AnswerService extends IService<Answer> {
    Integer getLastId();
}
