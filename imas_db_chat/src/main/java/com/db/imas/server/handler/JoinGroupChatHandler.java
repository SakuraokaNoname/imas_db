package com.db.imas.server.handler;

import com.db.imas.protocol.DataPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author noname
 * @Date 2021/8/31 17:44
 * @Version 1.0
 */
public class JoinGroupChatHandler extends SimpleChannelInboundHandler<DataPacket> {

    public static final JoinGroupChatHandler INSTANCE = new JoinGroupChatHandler();

    private JoinGroupChatHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataPacket packet){
//        System.out.println("groupChat");
//        GroupChatMessage message = JSON.parseObject(packet.getOriginalText(),GroupChatMessage.class);
//        System.out.println(message.getOriginalText() + "===");
//        ctx.writeAndFlush(new TextWebSocketFrame(packet.getOriginalText()));
    }

}
