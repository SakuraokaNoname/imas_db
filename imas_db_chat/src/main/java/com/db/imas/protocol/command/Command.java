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

    Byte GROUP_LIST = 4;

    Byte KEEPALIVE = 99;
}