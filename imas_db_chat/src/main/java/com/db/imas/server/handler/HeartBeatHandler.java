package com.db.imas.server.handler;

import com.alibaba.fastjson.JSON;
import com.db.imas.protocol.packet.HeartBeatMessage;
import com.db.imas.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.concurrent.TimeUnit;

import static com.db.imas.protocol.command.Command.KEEPALIVE;

/**
 * @Author noname
 * @Date 2021/8/28 15:17
 * @Version 1.0
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);

        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                HeartBeatMessage message = new HeartBeatMessage();
                message.setCommand(KEEPALIVE);
                message.setFlag(false);
                ctx.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
                scheduleSendHeartBeat(ctx);
            } else{
                System.out.println("用户:" + SessionUtil.getSession(ctx.channel()).getName() + " 下线");
                SessionUtil.unBindSession(ctx.channel());
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }

}
