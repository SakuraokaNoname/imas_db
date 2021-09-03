package com.db.imas.protocol.packet;

import com.db.imas.session.Session;

import static com.db.imas.protocol.command.Command.GROUP_CHAT;
import static com.db.imas.util.Constans.RECEIVE;

/**
 * @Author noname
 * @Date 2021/9/1 16:17
 * @Version 1.0
 */
public class GroupChatResponse {

    private Byte command = GROUP_CHAT;
    private String toGroupId;
    private String message;
    private String createTime;
    private Session sender;
    private Byte isReceive = RECEIVE;

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

    public Session getSender() {
        return sender;
    }

    public void setSender(Session sender) {
        this.sender = sender;
    }
}
