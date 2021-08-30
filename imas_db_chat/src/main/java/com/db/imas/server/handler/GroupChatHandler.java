package com.db.imas.server.handler;

import com.alibaba.fastjson.JSON;
import com.db.imas.protocol.DataPacket;
import com.db.imas.protocol.packet.GroupChatMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @Author noname
 * @Date 2021/8/28 17:44
 * @Version 1.0
 */
@ChannelHandler.Sharable
public class GroupChatHandler extends SimpleChannelInboundHandler<DataPacket> {

    public static final GroupChatHandler INSTANCE = new GroupChatHandler();

    private GroupChatHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataPacket packet){
        System.out.println("groupChat");
        GroupChatMessage message = JSON.parseObject(packet.getOriginalText(),GroupChatMessage.class);
        ctx.writeAndFlush(new TextWebSocketFrame(packet.getOriginalText()));
    }

}
