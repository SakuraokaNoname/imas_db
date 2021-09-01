package com.db.imas.protocol.packet;

/**
 * @Author noname
 * @Date 2021/8/31 11:36
 * @Version 1.0
 */
public class HeartBeatMessage {

    private Byte command;
    private boolean flag;

    public Byte getCommand() {
        return command;
    }

    public void setCommand(Byte command) {
        this.command = command;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
