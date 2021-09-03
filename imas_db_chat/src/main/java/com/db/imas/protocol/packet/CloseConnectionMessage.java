package com.db.imas.protocol.packet;

import com.db.imas.protocol.DataPacket;

import static com.db.imas.protocol.command.Command.CLOSE_CONNECTION;

/**
 * @Author noname
 * @Date 2021/9/3 16:21
 * @Version 1.0
 */
public class CloseConnectionMessage extends DataPacket {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Byte getCommand() {
        return CLOSE_CONNECTION;
    }
}
