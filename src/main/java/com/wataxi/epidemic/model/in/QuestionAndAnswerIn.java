package com.wataxi.epidemic.model.in;

import lombok.Data;

/**
 * @author yh200
 */
@Data
public class QuestionAndAnswerIn {
    private String question;
    private Integer type;
    private String answer;
    private Integer sort;
}
