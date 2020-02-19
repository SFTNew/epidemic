package com.wataxi.epidemic.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wataxi.epidemic.conmmon.R;
import com.wataxi.epidemic.entity.Answer;
import com.wataxi.epidemic.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author yh200
 */
@RestController
@RequestMapping("/as/v1")
public class AnswerController {
    @Autowired
    private AnswerService asService;

    @Transactional
    @DeleteMapping("/{id}")
    public R deleteAnswerById(@PathVariable Integer id){
        Answer byId = asService.getById(id);
        if(null == byId){
            return R.error(100,"删除失败");
        }
        byId.setSign(1);
        asService.updateById(byId);
        return R.success("");
    }

    @Transactional
    @PutMapping("/{id}")
    public R updateAnswerById(@PathVariable Integer id,@RequestParam("content") String content){
        if(!StringUtils.isNotEmpty(content)){
            return R.error(100,"修改内容不能为空");
        }
        Answer byId = asService.getById(id);
        if(null == byId){
            return R.error(100,"删除失败");
        }
        byId.setContent(content);
        asService.updateById(byId);
        return R.success("");
    }


}
