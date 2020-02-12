package com.wataxi.epidemic.model.in;

import lombok.Data;

/**
 * @author yh200
 */
@Data
public class QuestionIn {
    private Integer sort;
    private String question;
    private Integer status;
    private Integer type;
    private Integer qnid;
}
