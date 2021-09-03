package com.db.imas.protocol.packet;

import com.db.imas.session.Session;

import java.util.List;

import static com.db.imas.protocol.command.Command.PRODUCER_LIST;

/**
 * @Author noname
 * @Date 2021/9/3 9:49
 * @Version 1.0
 */
public class ProducerListResponse {

    private Byte command = PRODUCER_LIST;

    private List<Session> sessions;

    public Byte getCommand() {
        return command;
    }

    public void setCommand(Byte command) {
        this.command = command;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
