package com.wataxi.epidemic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wataxi.epidemic.conmmon.R;
import com.wataxi.epidemic.conmmon.ex.EpidemicException;
import com.wataxi.epidemic.entity.Questionnaire;
import com.wataxi.epidemic.model.in.QuestionAndAnswerIn;
import com.wataxi.epidemic.model.in.QuestionnaireIn;
import com.wataxi.epidemic.model.out.QuestionOut;
import com.wataxi.epidemic.service.AnswerService;
import com.wataxi.epidemic.service.QuestionService;
import com.wataxi.epidemic.service.QuestionnaireService;
import com.wataxi.epidemic.utils.StaticUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yh200
 */
@RestController
@RequestMapping("/que/v1")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService qsService;

    @GetMapping("/")
    public R getList(){
        List<Questionnaire> list = qsService.list(new QueryWrapper<Questionnaire>().eq("sign",0));
        return R.success(list);
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id){
        Questionnaire byId = qsService.getById(id);
        return R.success(byId);
    }

    @PostMapping("/q/{id}")
    public R addQuestionAndAnswer(@PathVariable Integer id ,@RequestBody QuestionAndAnswerIn qa){
        try {
            qsService.addQuestionAndAnswer(id,qa);
        } catch (EpidemicException e) {
            e.printStackTrace();
            return R.error(500,"服务器内部错误");
        }
        return  R.success("");
    }

    @GetMapping("/push/{id}")
    public  R pushQuestionnaire(@PathVariable Integer id){
        Questionnaire byId = qsService.getById(id);
        if(null == byId){
            return R.error(200,"发布失败，问卷调查不存在");
        }
        List<QuestionOut> questionsByQnId = qsService.getQuestionsByQnId(id);
        if(null == questionsByQnId || questionsByQnId.size()<=0){
            return R.error(200,"发布失败，发布内容不能为空");
        }
        String webName =byId.getTitle();
        Context context = new Context();
        context.setVariable("webName",webName);
        context.setVariable("questionContent",byId.getContent());
        Map<String,Object> questions = questionsByQnId.stream().collect(Collectors.toMap(QuestionOut::getQsId,a->a,(k1,k2)->k1));
        context.setVariables(questions);
        try {
            StaticUtils.execHTML(webName,context);
        } catch (Exception e) {
            e.printStackTrace();
            return  R.error(300,e.getMessage());
        }
        return  R.success("生成成功");
    }

    @GetMapping("/q/{id}")
    public R getQuestionsById(@PathVariable Integer id){
        List<QuestionOut> questionsByQnId = qsService.getQuestionsByQnId(id);
        return  R.success(questionsByQnId);
    }

    @Transactional
    @PostMapping("/")
    public R addQuestionnaire(@RequestBody QuestionnaireIn in){
        Questionnaire qs = new Questionnaire();
        qs.setId(qsService.getLastId());
        qs.setTitle(in.getQuestion());
        qs.setContent(in.getContent());
        qs.setSign(0);
        qs.setStatus(0);
        qs.setCreateTime(new Date());
        try{
            qsService.save(qs);
        }catch (Exception e){
            return R.error(500,"服务器内部错误");
        }
        return R.success(qs.getId());
    }

    @Transactional
    @PutMapping("/{id}")
    public R updateQuestionnaire(@PathVariable("id") Integer id,@RequestBody QuestionnaireIn in){
        Questionnaire byId = qsService.getById(id);
        if(byId ==null){
            return  R.error(1,"修改错误");
        }
        byId.setStatus(in.getStatus()==null?byId.getStatus():in.getStatus());
        byId.setTitle(StringUtils.isNotEmpty(in.getQuestion())?in.getQuestion():byId.getTitle());
        byId.setContent(StringUtils.isNotEmpty(in.getContent())?in.getContent():byId.getContent());
        byId.setUpdateTime(new Date());
        try{
            qsService.updateById(byId);
        }catch (Exception e){
            return R.error(500,"服务器内部错误");
        }
        return R.success("");
    }

    @Transactional
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id")Integer id){
        Questionnaire byId = qsService.getById(id);
        if(byId ==null){
            return  R.error(1,"未找到要删除的记录");
        }
        byId.setSign(1);
        try {
            qsService.updateById(byId);
        }catch (Exception e){
            return R.error(500,"服务器内部错误");
        }
        return R.success("");
    }

}
