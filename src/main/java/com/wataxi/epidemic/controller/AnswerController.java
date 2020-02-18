package com.wataxi.epidemic.controller;

import com.wataxi.epidemic.conmmon.R;
import com.wataxi.epidemic.entity.Answer;
import com.wataxi.epidemic.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
