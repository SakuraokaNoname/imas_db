package com.db.imas.server.handler;

import com.alibaba.fastjson.JSON;
import com.db.imas.protocol.DataPacket;
import com.db.imas.protocol.packet.JoinGroupMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @Author noname
 * @Date 2021/8/31 17:44
 * @Version 1.0
 */
public class JoinGroupHandler extends SimpleChannelInboundHandler<DataPacket> {

    public static final JoinGroupHandler INSTANCE = new JoinGroupHandler();

    private JoinGroupHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataPacket packet){
        JoinGroupMessage message = JSON.parseObject(packet.getOriginalText(),JoinGroupMessage.class);


        ctx.writeAndFlush(new TextWebSocketFrame());
    }

}
