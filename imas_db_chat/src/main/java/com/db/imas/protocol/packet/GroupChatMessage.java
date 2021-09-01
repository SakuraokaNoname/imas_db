package com.db.imas.protocol.packet;

import com.db.imas.protocol.DataPacket;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import static com.db.imas.protocol.command.Command.GROUP_CHAT;

/**
 * @Author noname
 * @Date 2021/8/28 17:37
 * @Version 1.0
 */
public class GroupChatMessage extends DataPacket {

    private String toGroupId;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    private String senderId;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getToGroupId() {
        return toGroupId;
    }

    public void setToGroupId(String toGroupId) {
        this.toGroupId = toGroupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return GROUP_CHAT;
    }

    @Override
    public String toString() {
        return "GroupChatMessage{" +
                "toGroupId='" + toGroupId + '\'' +
                ", message='" + message + '\'' +
                ", createTime=" + createTime +
                ", senderId='" + senderId + '\'' +
                '}';
    }
}
