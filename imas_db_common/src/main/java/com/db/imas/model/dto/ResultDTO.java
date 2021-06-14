package com.db.imas.model.dto;

import com.db.imas.constans.ErrorCode;

/**
 * @Author noname
 * @Date 2021/6/11 16:03
 * @Version 1.0
 */
public class ResultDTO<T> {

    private String code;

    private String message;

    private T data;

    public ResultDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultDTO(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultDTO success() {
        return new ResultDTO(ErrorCode.SUCCESS.getCode(),ErrorCode.SUCCESS.getMessage());
    }
    public static <T> ResultDTO fail() {
        return new ResultDTO(ErrorCode.ERROR.getCode(),ErrorCode.ERROR.getMessage());
    }

    public static <T> ResultDTO success(T data) {
        return new ResultDTO(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
