package com.db.imas.server.handler;

import com.db.imas.protocol.DataPacket;
import com.db.imas.protocol.DataPacketCodec;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.HashMap;
import java.util.Map;

import static com.db.imas.protocol.command.Command.*;

/**
 * @Author noname
 * @Date 2021/8/28 15:16
 * @Version 1.0
 * <p>
 *
 * 处理消息的handler
 * TextWebSocketFrame：在netty中，是用于websocket专门处理文本的对象，frame是消息的载体。
 */
@ChannelHandler.Sharable //实现单例的注解
public class MainHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static final MainHandler INSTANCE = new MainHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends DataPacket>> handlerMap;

    private MainHandler() {
        handlerMap = new HashMap<>();
        handlerMap.put(CONNECTION, ConnectionHandler.INSTANCE);
        handlerMap.put(PRODUCER_LIST, ProducerListHandler.INSTANCE);
        handlerMap.put(GROUP_CHAT, GroupChatHandler.INSTANCE);
        handlerMap.put(GROUP_JOIN, JoinGroupHandler.INSTANCE);
        handlerMap.put(BE_ONLINE, BeOnlineHandler.INSTANCE);
        handlerMap.put(OFF_LINE_CHAT_MESSAGE, OffLineChatMessageHandler.INSTANCE);
        handlerMap.put(KEEPALIVE, KeepAliveHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String text = msg.text();
        DataPacket packet = DataPacketCodec.INSTANCE.decode(text);
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }

}
