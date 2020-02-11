package com.wataxi.epidemic.entity;

import lombok.Data;
import java.util.Date;

/**
 * @author yh200
 */
@Data
public class Question {
    private Integer id;
    private Integer sort;
    private String title;
    private Integer status;
    private Integer type;
    private Integer qnid;
    private Integer sign;
    private Date createTime;
    private Date updateTime;
}
