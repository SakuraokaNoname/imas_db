package com.db.imas.protocol.packet;

import com.db.imas.protocol.DataPacket;

import static com.db.imas.protocol.command.Command.CONNECTION;

/**
 * @Author noname
 * @Date 2021/8/31 15:41
 * @Version 1.0
 */
public class ConnectionMessage extends DataPacket {

    private String id;
    private String name;
    private String icon;
    private String chatId;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public Byte getCommand() {
        return CONNECTION;
    }
}
