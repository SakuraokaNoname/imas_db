package com.db.imas.protocol.command;

/**
 * @Author noname
 * @Date 2021/8/16 16:58
 * @Version 1.0
 */
public interface Command {

    Byte CONNECTION = 1;

    Byte GROUP_CHAT = 3;

    Byte PRODUCER_LIST = 40;

    Byte GROUP_LIST = 50;

    Byte GROUP_JOIN = 51;

    Byte BE_ONLINE = 90;

    Byte OFF_LINE_CHAT_MESSAGE = 91;

    Byte KEEPALIVE = 99;
}