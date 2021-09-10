package com.db.imas.server.handler;

import com.alibaba.fastjson.JSON;
import com.db.imas.protocol.DataPacket;
import com.db.imas.protocol.packet.ProducerListResponse;
import com.db.imas.session.Session;
import com.db.imas.util.GroupUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

import static com.db.imas.protocol.command.Command.PRODUCER_LIST;

/**
 * @Author noname
 * @Date 2021/9/3 9:55
 * @Version 1.0
 * 返回所有用户的缓存
 */
public class ProducerListHandler extends SimpleChannelInboundHandler<DataPacket> {

    public static final ProducerListHandler INSTANCE = new ProducerListHandler();

    private ProducerListHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataPacket packet){
        List<Session> producerList = GroupUtil.getProducerList();
        ProducerListResponse response = new ProducerListResponse();
        response.setCommand(PRODUCER_LIST);
        response.setSessions(producerList);
        ctx.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(response)));
    }

}
