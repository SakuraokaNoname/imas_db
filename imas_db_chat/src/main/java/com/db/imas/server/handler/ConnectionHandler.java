package com.db.imas.server.handler;

import com.alibaba.fastjson.JSON;
import com.db.imas.model.entity.ImasGroupChat;
import com.db.imas.protocol.DataPacket;
import com.db.imas.protocol.packet.ConnectionMessage;
import com.db.imas.protocol.packet.GroupListMessage;
import com.db.imas.session.Session;
import com.db.imas.util.GroupUtil;
import com.db.imas.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author noname
 * @Date 2021/8/31 15:34
 * @Version 1.0
 */
public class ConnectionHandler extends SimpleChannelInboundHandler<DataPacket> {

    public static final ConnectionHandler INSTANCE = new ConnectionHandler();

    protected ConnectionHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataPacket packet) {
        System.out.println(packet.getOriginalText());
        ConnectionMessage connectionMessage = JSON.parseObject(packet.getOriginalText(), ConnectionMessage.class);
        SessionUtil.bindSession(new Session(connectionMessage.getId(),connectionMessage.getName(),connectionMessage.getIcon(),connectionMessage.getChatId()), ctx.channel());

        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        List<ImasGroupChat> imasGroupChats = GroupUtil.getGroupList();
        List<GroupListMessage> groupListMessageList = new ArrayList<>();
        List<String> producerList = new ArrayList<>();
        for(ImasGroupChat imasGroupChat : imasGroupChats){
            GroupListMessage groupListMessage = new GroupListMessage(imasGroupChat.getId(),imasGroupChat.getTitle(),imasGroupChat.getInfo(),imasGroupChat.getIcon(),imasGroupChat.getChatId());
            for(String member : imasGroupChat.getMember().split(",")){
                if(!StringUtils.isEmpty(member)){
                    producerList.add(member);
                    if(connectionMessage.getId().equals(member)){
                        Channel channel = SessionUtil.getChannel(connectionMessage.getChatId());
                        if(channel != null){
                            channelGroup.add(ctx.channel());
                            SessionUtil.bindChannelGroup(imasGroupChat.getChatId(), channelGroup);
                        }
                    }
                }
            }
            groupListMessage.setProducers(producerList);
            groupListMessageList.add(groupListMessage);
        }
        ctx.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(groupListMessageList)));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
