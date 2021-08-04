package com.db.imas.model.dto;

import com.db.imas.constans.ErrorCode;

/**
 * @Author noname
 * @Date 2021/6/11 16:03
 * @Version 1.0
 */
public class UploadDTO {

    private boolean success;

    private String error;

    private String url;

    public UploadDTO(boolean success, String error, String url) {
        this.success = success;
        this.error = error;
        this.url = url;
    }

    public static UploadDTO success(String url) {
        return new UploadDTO(true,"",url);
    }
    public static UploadDTO error() {
        return new UploadDTO(false,"上传失败","");
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
