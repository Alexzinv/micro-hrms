package com.alex.common.exception;

import com.alex.common.consant.ResultCodeEnum;
import lombok.Getter;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/9
 * @Description 自定义异常
 */
@Getter
public class HRMSException extends RuntimeException {

    /**
     * 异常代码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 返回状态枚举
     */
    private ResultCodeEnum resultCodeEnum;

    public HRMSException(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public HRMSException(ResultCodeEnum resultCodeEnum){
        this.resultCodeEnum = resultCodeEnum;
    }
}
