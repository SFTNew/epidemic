package com.wataxi.epidemic.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.context.Context;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StaticUtilsTest {

    @Test
    public void exeHtml(){
        Context con = new Context();
        con.setVariable("webName","webName");
        con.setVariable("questionContent","byId.getContent()");
        try {
            StaticUtils.execHTML("名字",con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}