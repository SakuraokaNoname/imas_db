package com.db.imas.constans;

/**
 * @Author noname
 * @Date 2021/6/11 16:06
 * @Version 1.0
 */
public enum ErrorCode {
    SUCCESS("200", "success"),
    ACCESS_LIMIT("7650","禁止访问"),
    PAGE_TURNING_ERROR("9989","已经不能再翻页了"),
    SEARCH_PARAM_NULL("9990","请选择要搜索的偶像"),
    UPLOAD_NOT_PARAMS("9991","上传参数有误"),
    UPLOAD_ERROR("9992","上传失败"),
    PERMISSION_FAIL("9993","权限不足"),
    USERNAME_IS_REPEAT("9994", "用户名已被占用"),
    USER_UPDATE_FAIL("9994", "更新失败"),
    LOGINID_IS_CHINESE("9995", "登录账号不能有中文"),
    USER_NO_ONE("9995", "账号已被注册"),
    TOKEN_EXPIRE("9996", "请重新登录"),
    REGISTER_ERROR("9997", "注册失败"),
    NO_USER("9998","没有用户信息"),
    LOGIN_ERROR("9998", "账号或密码有误"),
    LOGIN_BLOCK("9998", "账号已被屏蔽"),
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
