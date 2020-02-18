package com.wataxi.epidemic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wataxi.epidemic.conmmon.R;
import com.wataxi.epidemic.entity.Question;
import com.wataxi.epidemic.model.in.QuestionIn;
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

    @GetMapping("/")
    public R list(){
        List<Question> list = qService.list(new QueryWrapper<Question>().eq("sign",0));
        return R.success(list);
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id){
        Question byId = qService.getById(id);
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
    public R updateQuestion(@PathVariable Integer id,@RequestBody QuestionIn in){
        Question byId = qService.getById(id);
        if(null == byId){
            return R.error(100,"数据不存在");
        }
        byId.setQnid(in.getQnid());
        byId.setSort(in.getSort());
        byId.setStatus(in.getStatus());
        byId.setTitle(in.getQuestion());
        byId.setType(in.getType());
        byId.setUpdateTime(new Date());
        qService.updateById(byId);
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
