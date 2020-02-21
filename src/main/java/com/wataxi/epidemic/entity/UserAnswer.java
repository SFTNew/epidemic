package com.wataxi.epidemic.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author yh200
 */
@Data
@Accessors(chain = true)
public class UserAnswer {
    private Integer id;
    private Integer qnid;
    private Integer qid;
    private String answer;
    private Integer asType;
    private String token;
    private Integer sign;
    private Date createTime;
}
