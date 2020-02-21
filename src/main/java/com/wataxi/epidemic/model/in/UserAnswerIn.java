package com.wataxi.epidemic.model.in;

import lombok.Data;

import java.util.List;

/**
 * @author yh200
 */
@Data
public class UserAnswerIn {
    private List<AnswerIn> answers;
}
