package com.wataxi.epidemic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wataxi.epidemic.controller.QuestionnaireController;
import com.wataxi.epidemic.entity.Answer;
import com.wataxi.epidemic.service.AnswerService;
import com.wataxi.epidemic.utils.PasswordEncryption;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EpidemicApplicationTests {

//    @Autowired
//    QuestionnaireController controller;
    @Autowired
    private AnswerService service;
    @Test
    void contextLoads() {
//        controller.pushQuestionnaire(2);
//        System.out.println(PasswordEncryption.encode("admin")) ;
        Answer answer = new Answer();
        answer.setSign(1);
//            asService.update(new UpdateWrapper<Answer>().set("sign","1").eq("qid",byId.getId()));
        service.update(answer,new QueryWrapper<Answer>().eq("qid","1"));

    }

}
