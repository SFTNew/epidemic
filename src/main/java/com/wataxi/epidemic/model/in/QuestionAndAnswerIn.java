package com.wataxi.epidemic.model.in;

import com.wataxi.epidemic.model.out.AnswerOut;
import lombok.Data;

import java.util.List;

/**
 * @author yh200
 */
@Data
public class QuestionAndAnswerIn {
    private String question;
    private Integer type;
    private List<AnswerOut> answers;
    private Integer sort;
}
