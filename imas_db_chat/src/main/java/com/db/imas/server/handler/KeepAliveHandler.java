package com.db.imas.server.handler;

import com.alibaba.fastjson.JSON;
import com.db.imas.protocol.DataPacket;
import com.db.imas.protocol.packet.KeepAliveMessage;
import com.db.imas.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @Author noname
 * @Date 2021/8/31 10:42
 * @Version 1.0
 */
@ChannelHandler.Sharable
public class KeepAliveHandler extends SimpleChannelInboundHandler<DataPacket> {

    public static final KeepAliveHandler INSTANCE = new KeepAliveHandler();

    private KeepAliveHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataPacket packet){
        KeepAliveMessage message = JSON.parseObject(packet.getOriginalText(), KeepAliveMessage.class);
        SessionUtil.initOverTime(ctx.channel());
        ctx.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
    }

}
