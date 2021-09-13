package com.db.imas.protocol.packet;

import com.db.imas.protocol.DataPacket;

import static com.db.imas.protocol.command.Command.GROUP_JOIN;

/**
 * @Author noname
 * @Date 2021/8/31 17:44
 * @Version 1.0
 */
public class JoinGroupMessage extends DataPacket {

    private String id;
    private String chatId;

    @Override
    public Byte getCommand() {
        return GROUP_JOIN;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
