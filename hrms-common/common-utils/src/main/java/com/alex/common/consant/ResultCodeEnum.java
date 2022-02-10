package com.alex.common.consant;

/**
 * @Author _Alexzinv_
 * @Date 2021/9/9
 * @Description 返回码
 */
public enum ResultCodeEnum {
    /** 成功 */
    SUCCESS(20000, "操作成功"),
    /** 失败 */
    ERROR( 20001, "操作失败"),
    /** 未登录 */
    UNAUTHENTICATED( 20002, "未登录"),
    /** 权限不足 */
    INSUFFICIENT_PERMISSIONS( 20003, "权限不足"),
    /** 参数格式异常 */
    INVALID_EXCEPTION( 20004, "参数格式校验失败"),
    /** 未知异常 */
    UNKNOWN_EXCEPTION(20005, "未知异常"),
    /** 账号或密码错误 */
    ACCOUNT_OR_PASSWORD_WRONG(20006, "账号或密码错误"),
    /** 名字已存在 */
    EXISTS_EXCEPTION(20007, "名字已存在"),
    /** 系统繁忙 */
    SYSTEM_BUSY(20008, "系统繁忙"),
    /** 公司Id */
    NO_COMPANY_ID(20009, "没有公司Id"),
    /** 远程事务异常 */
    GLOBAL_TRANSACTIONAL_EXCEPTION(20010, "远程事务保存失败"),
    /** 网关鉴权失败 */
    GATEWAY_AUTH_EXCEPTION(20011, "网关鉴权失败");

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

    /** 根据code获取描述 */
    public static String getMessageByCode(Integer code){
        for (ResultCodeEnum r : ResultCodeEnum.values()) {
            if(r.getCode().equals(code)){
                return r.getMessage();
            }
        }
        return null;
    }
}
