package com.db.imas.protocol.packet;

import com.db.imas.protocol.DataPacket;

import static com.db.imas.protocol.command.Command.KEEPALIVE;

/**
 * @Author noname
 * @Date 2021/8/31 10:46
 * @Version 1.0
 */
public class KeepAliveMessage extends DataPacket {

    private boolean flag;
    private Byte command;

    public KeepAliveMessage() {
        this.flag = false;
        this.command = KEEPALIVE;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setCommand() {
        this.command = KEEPALIVE;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public Byte getCommand() {
        return KEEPALIVE;
    }

}
