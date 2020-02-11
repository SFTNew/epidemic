package com.wataxi.epidemic.conmmon;

import lombok.Data;
/**
 * @author yh200
 */
@Data
public class R {
    private Integer code;
    private String msg;
    private Object data;

    public R(){
    }

    public R(Integer code,String msg ,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static R success(Object data){
        return new R(0,"success",data);
    }

    public static R error(Integer code,String msg){
        return new R(code,msg,null);
    }

}
