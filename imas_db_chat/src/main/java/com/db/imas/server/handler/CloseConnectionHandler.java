package com.db.imas.server.handler;

import com.db.imas.protocol.DataPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author noname
 * @Date 2021/9/3 16:22
 * @Version 1.0
 */
public class CloseConnectionHandler extends SimpleChannelInboundHandler<DataPacket> {

    public static final CloseConnectionHandler INSTANCE = new CloseConnectionHandler();

    protected CloseConnectionHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataPacket packet) {
        System.out.println("用户下线");
        System.out.println(packet.getOriginalText());
    }

}
