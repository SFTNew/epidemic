package com.wataxi.epidemic.model.in;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yh200
 */
@Data
public class QuestionnaireIn {
    @NotNull
    private String question;
    @NotNull
    private String content;
    private Integer status;
}
