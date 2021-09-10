package com.db.imas.protocol.packet;

import static com.db.imas.protocol.command.Command.GROUP_CHAT;
import static com.db.imas.util.Constans.RECEIVE;

/**
 * @Author noname
 * @Date 2021/9/1 16:17
 * @Version 1.0
 */
public class GroupChatResponse {

    private Byte command = GROUP_CHAT;
    private Long id;
    private String toGroupId;
    private String message;
    private String createTime;
    private String sender;
    private Byte isReceive = RECEIVE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(Byte isReceive) {
        this.isReceive = isReceive;
    }

    public Byte getCommand() {
        return command;
    }

    public void setCommand(Byte command) {
        this.command = command;
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
