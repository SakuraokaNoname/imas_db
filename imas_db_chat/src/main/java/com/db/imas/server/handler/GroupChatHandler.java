package com.db.imas.server.handler;

import com.alibaba.fastjson.JSON;
import com.db.imas.protocol.DataPacket;
import com.db.imas.protocol.packet.GroupChatMessage;
import com.db.imas.protocol.packet.GroupChatResponse;
import com.db.imas.session.Session;
import com.db.imas.util.DateUtil;
import com.db.imas.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Date;

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
        System.out.println(packet.getOriginalText());
        GroupChatMessage message = JSON.parseObject(packet.getOriginalText(),GroupChatMessage.class);
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(message.getToGroupId());
        GroupChatResponse response = new GroupChatResponse();

        response.setCreateTime(DateUtil.getNowTime());
        response.setMessage(message.getMessage());
        response.setToGroupId(message.getToGroupId());
        response.setSender(SessionUtil.getSession(SessionUtil.getChannel(message.getSenderId())));
        System.out.println(channelGroup.size());
        channelGroup.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(response)));
    }

}
