package com.wataxi.epidemic.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author yh200
 */
@Data
public class Questionnaire {
    private Integer id;
    private String title;
    private String content;
    private Integer status;
    private Integer sign;
    private Date createTime;
    private Date updateTime;
}
