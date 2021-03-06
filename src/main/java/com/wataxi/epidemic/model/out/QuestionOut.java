package com.wataxi.epidemic.model.out;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author yh200
 */
@Data
public class QuestionOut {
    private Integer id;
    private String title;
    private Integer status;
    private Date createTime;
    private Integer type;
    private List<AnswerOut> answers;

    public String getQsId(){
        return id.toString();
    }
}
