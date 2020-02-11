package com.wataxi.epidemic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wataxi.epidemic.conmmon.R;
import com.wataxi.epidemic.entity.Questionnaire;
import com.wataxi.epidemic.model.in.QuestionnaireIn;
import com.wataxi.epidemic.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
        return R.success("");
    }

    @PutMapping("/{id}")
    public R updateQuestionnaire(@PathVariable("id") Integer id,@RequestBody QuestionnaireIn in){
        Questionnaire byId = qsService.getById(id);
        if(byId ==null){
            return  R.error(1,"修改错误");
        }
        byId.setStatus(in.getStatus());
        byId.setTitle(in.getQuestion());
        byId.setContent(in.getContent());
        try{
            qsService.updateById(byId);
        }catch (Exception e){
            return R.error(500,"服务器内部错误");
        }
        return R.success("");
    }

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
