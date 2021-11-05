package com.db.imas.server.handler;

import com.alibaba.fastjson.JSON;
import com.db.imas.protocol.DataPacket;
import com.db.imas.protocol.packet.JoinGroupMessage;
import com.db.imas.service.GroupChatService;
import com.db.imas.session.Session;
import com.db.imas.util.GroupUtil;
import com.db.imas.util.ServiceUtil;
import com.db.imas.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.security.acl.Group;

/**
 * @Author noname
 * @Date 2021/8/31 17:44
 * @Version 1.0
 */
public class JoinGroupHandler extends SimpleChannelInboundHandler<DataPacket> {

    public static final JoinGroupHandler INSTANCE = new JoinGroupHandler();

    private GroupChatService groupChatService;

    private JoinGroupHandler() {
        groupChatService = ServiceUtil.getBean(GroupChatService.class);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataPacket packet){
        JoinGroupMessage message = JSON.parseObject(packet.getOriginalText(),JoinGroupMessage.class);
        String id = message.getId();
        String chatId = message.getChatId();
        GroupUtil.addGroupMember(id,chatId);
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(chatId);
        channelGroup.add(ctx.channel());
        SessionUtil.bindChannelGroup(chatId,channelGroup);
        groupChatService.insertGroupMember(message);

        //加入群聊之后,将用户信息塞入用户列表
        GroupUtil.initProducerMap(groupChatService.getProducer(id));

        BeOnlineHandler.INSTANCE.channelRead0(ctx,packet);
    }

}
