package com.db.imas.constans;

/**
 * @Author noname
 * @Date 2021/6/11 16:06
 * @Version 1.0
 */
public enum ErrorCode {
    SUCCESS("200", "调用成功"),
    USER_UPDATE_FAIL("9994", "更新失败"),
    USER_NO_ONE("9995", "用户名已被占用"),
    TOKEN_EXPIRE("9996", "请重新登录"),
    REGISTER_ERROR("9997", "注册失败"),
    LOGIN_ERROR("9998", "账号或密码有误"),
    ERROR("9999", "系统异常"),;

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
