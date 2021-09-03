package com.db.imas.protocol.packet;

import com.db.imas.protocol.DataPacket;

import static com.db.imas.protocol.command.Command.PRODUCER_LIST;

/**
 * @Author noname
 * @Date 2021/9/3 10:29
 * @Version 1.0
 */
public class BasicMessage extends DataPacket {

    @Override
    public Byte getCommand() {
        return PRODUCER_LIST;
    }

}
