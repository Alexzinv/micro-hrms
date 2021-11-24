package com.alex.common.consant;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/9
 * @Description 返回码
 */
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(20000, "操作成功"),
    /**
     * 失败
     */
    ERROR( 20001, "操作失败"),
    /**
     * 未登录
     */
    UNAUTHENTICATED( 20002, "未登录"),
    /**
     * 权限不足
     */
    INSUFFICIENT_PERMISSIONS( 20003, "权限不足"),
    /**
     * 参数格式异常
     */
    INVALID_EXCEPTION( 20004, "参数格式校验失败"),
    /**
     * 未知异常
     */
    UNKNOWN_EXCEPTION(20005, "未知异常"),
    /**
     * 账号或密码错误
     */
    ACCOUNT_OR_PASSWORD_WRONG(20006, "账号或密码错误"),
    /**
     * 名字已存在
     */
    EXISTS_EXCEPTION(20007, "名字已存在");
    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 消息
     */
    private final String message;

    ResultCodeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
        return code;
    }



    public String getMessage(){
        return message;
    }
}
