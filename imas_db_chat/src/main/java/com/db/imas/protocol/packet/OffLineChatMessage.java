package com.db.imas.protocol.packet;

import com.db.imas.protocol.DataPacket;

import static com.db.imas.protocol.command.Command.OFF_LINE_CHAT_MESSAGE;

/**
 * @Author noname
 * @Date 2021/9/8 10:15
 * @Version 1.0
 */
public class OffLineChatMessage extends DataPacket {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Byte getCommand() {
        return OFF_LINE_CHAT_MESSAGE;
    }
}
