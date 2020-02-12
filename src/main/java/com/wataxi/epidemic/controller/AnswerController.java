package com.wataxi.epidemic.controller;

import com.wataxi.epidemic.conmmon.R;
import com.wataxi.epidemic.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
