package com.db.imas.session;

import static com.db.imas.util.Constans.PRODUCER_OFFLINE;

/**
 * @Author noname
 * @Date 2021/9/2 15:11
 * @Version 1.0
 */
public class Producer {

    private int id;
    private Byte isOnline = PRODUCER_OFFLINE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Byte getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Byte isOnline) {
        this.isOnline = isOnline;
    }
}
