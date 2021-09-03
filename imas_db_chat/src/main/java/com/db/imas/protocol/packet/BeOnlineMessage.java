package com.db.imas.protocol.packet;

import com.db.imas.protocol.DataPacket;

import static com.db.imas.protocol.command.Command.BE_ONLINE;

/**
 * @Author noname
 * @Date 2021/9/3 15:07
 * @Version 1.0
 */
public class BeOnlineMessage extends DataPacket {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Byte getCommand() {
        return BE_ONLINE;
    }
}
