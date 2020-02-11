package com.wataxi.epidemic.entity;

import lombok.Data;

/**
 * @author yh200
 */
@Data
public class Answer {
    private Integer id;
    private Integer pid;
    private Integer sort;
    private String content;
    private Integer status;
    private Integer sign;
}
