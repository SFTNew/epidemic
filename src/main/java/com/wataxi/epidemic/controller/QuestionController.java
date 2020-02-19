package com.wataxi.epidemic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wataxi.epidemic.conmmon.R;
import com.wataxi.epidemic.entity.Answer;
import com.wataxi.epidemic.entity.Question;
import com.wataxi.epidemic.model.in.QuestionAndAnswerIn;
import com.wataxi.epidemic.model.in.QuestionIn;
import com.wataxi.epidemic.model.out.AnswerOut;
import com.wataxi.epidemic.model.out.QuestionOut;
import com.wataxi.epidemic.service.AnswerService;
import com.wataxi.epidemic.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author yh200
 */
@RestController
@RequestMapping("qs/v1")
public class QuestionController {

    @Autowired
    private QuestionService qService;
    @Autowired
    private AnswerService asService;

    @GetMapping("/")
    public R list(){
        List<Question> list = qService.list(new QueryWrapper<Question>().eq("sign",0));
        return R.success(list);
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id){
        QuestionOut byId = qService.getQuestionById(id);
        return R.success(byId);
    }

    @Transactional
    @PostMapping("/")
    public R addQuestion(@RequestBody QuestionIn in){
        Question qs = new Question();
        qs.setId(1);
        qs.setTitle(in.getQuestion());
        qs.setType(in.getType());
        qs.setStatus(in.getStatus());
        qs.setSort(in.getSort());
        qs.setQnid(in.getQnid());
        qs.setSign(0);
        qs.setCreateTime(new Date());
        qService.save(qs);
        return R.success("");
    }

    @Transactional
    @PutMapping("/{id}")
    public R updateQuestion(@PathVariable Integer id,@RequestBody QuestionAndAnswerIn in){
        Question byId = qService.getById(id);
        if(null == byId){
            return R.error(100,"数据不存在");
        }
        byId.setSort(in.getSort()==null?byId.getSort():in.getSort());
        byId.setTitle(StringUtils.isNotEmpty(in.getQuestion())?in.getQuestion():byId.getTitle());
        byId.setType(in.getType()==null?byId.getType():in.getType());
        byId.setUpdateTime(new Date());
        qService.updateById(byId);
        if(in.getType()==2){
            Answer answer = new Answer();
            answer.setSign(1);
            asService.update(answer,new QueryWrapper<Answer>().eq("qid",byId.getId()));
        }
        List<AnswerOut> answerOuts = in.getAnswers();
        if(null !=answerOuts && answerOuts.size()>0){
            for (AnswerOut aw : answerOuts) {
                Answer answer = new Answer();
                answer.setContent(aw.getContent());
                answer.setSort(aw.getSort());
                if(null == aw.getId() || aw.getId() == 0){
                    answer.setSign(0);
                    answer.setQid(byId.getId());
                    answer.setStatus(0);
                    answer.setId(asService.getLastId());
                    asService.save(answer);
                }else{
                    answer.setId(aw.getId());
                    asService.updateById(answer);
                }
            }
        }
        return  R.success("");
    }

    @DeleteMapping("/{id}")
    public R deleteQuestion(@PathVariable Integer id){
        try {
            if(!qService.deleteQuestion(id)){
                return R.error(100,"删除失败");
            }
        }catch (Exception e){
            return R.error(100,"删除错误");
        }
        return  R.success("");
    }
}
