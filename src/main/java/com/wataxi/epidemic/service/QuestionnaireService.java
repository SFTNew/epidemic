package com.wataxi.epidemic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wataxi.epidemic.entity.Questionnaire;

/**
 * @author yh200
 */
public interface QuestionnaireService extends IService<Questionnaire> {
    public Integer getLastId();
}
