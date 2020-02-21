package com.wataxi.epidemic.controller;

import com.wataxi.epidemic.conmmon.R;
import com.wataxi.epidemic.entity.Questionnaire;
import com.wataxi.epidemic.entity.UserAnswer;
import com.wataxi.epidemic.model.in.AnswerIn;
import com.wataxi.epidemic.model.in.UserAnswerIn;
import com.wataxi.epidemic.service.QuestionnaireService;
import com.wataxi.epidemic.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author yh200
 */
@RestController
@RequestMapping("/question/v1")
public class UserAnswerController {

    @Autowired
    private UserAnswerService service;
    @Autowired
    private QuestionnaireService questionnaireService;

    @Transactional
    @PostMapping("/answer/{id}")
    public R saveQuestionnaireAnswer(@PathVariable Integer id, @RequestBody UserAnswerIn in){
        Questionnaire byId = questionnaireService.getById(id);
        try{
            if(null != byId && byId.getSign() == 0 && byId.getStatus() == 0 ) {
                if (null != in.getAnswers() && in.getAnswers().size() > 0) {
                    in.getAnswers().forEach(as -> {
                        UserAnswer us = new UserAnswer();
                        us.setId(service.getLastId())
                                .setQnid(id)
                                .setQid(as.getName())
                                .setCreateTime(new Date())
                                .setAnswer(as.getValue())
                                .setSign(0);
                        service.save(us);
                    });
                }
            }
        }catch (Exception e){
            return R.error(500,e.getMessage());
        }
        return  R.success("");
    }
}
