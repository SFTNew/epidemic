package com.wataxi.epidemic.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yh200
 */
@Controller
public class WebController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/t")
    public String template(){
        return "templateFiles/template";
    }

}
