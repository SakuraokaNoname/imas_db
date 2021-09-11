package com.db.imas.model.entity;

import java.util.Date;

/**
 * @Author noname
 * @Date 2021/9/9 17:56
 * @Version 1.0
 */
public class ImasChatMessage {

    private Long id;
    private String type;
    private String message;
    private String toChatId;
    private String createTime;
    private String sender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToChatId() {
        return toChatId;
    }

    public void setToChatId(String toChatId) {
        this.toChatId = toChatId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
