package com.db.imas.protocol.command;

/**
 * @Author noname
 * @Date 2021/8/16 16:58
 * @Version 1.0
 */
public interface Command {

    Byte CONNECTION = 1;

    Byte JOIN_GROUP_CHAT = 2;

    Byte GROUP_CHAT = 3;

    Byte PRODUCER_LIST = 40;

    Byte GROUP_LIST = 50;

    Byte BE_ONLINE = 90;

    Byte CLOSE_CONNECTION = 50;

    Byte KEEPALIVE = 99;
}