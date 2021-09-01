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
import java.util.Map;

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
        List<GroupListMessage> groupListMessageList = bindGroupChannel(channelGroup);

        System.out.println("size:" + SessionUtil.getChannelGroup("test:99999999").size());
        Map<String, Channel> userChannelMap = SessionUtil.getUserChannelMap();
        System.out.println(userChannelMap.size());
        userChannelMap.forEach((key,value) -> {
            System.out.println(key + "---" + value);
        });
        ctx.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(groupListMessageList)));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }

    public List<GroupListMessage> bindGroupChannel(ChannelGroup channelGroup){
        List<GroupListMessage> groupListMessageList = new ArrayList<>();
        List<ImasGroupChat> imasGroupChats = GroupUtil.getGroupList();
        for(ImasGroupChat groupChat : imasGroupChats){
            String groupChatId = groupChat.getChatId();
            List<String> members = new ArrayList<>();
            for(String member : groupChat.getMember().split(",")){
                if(!StringUtils.isEmpty(member)){
                    members.add(member);
                    Channel channel = SessionUtil.getChannel(member);
                    if(channel != null){
                        channelGroup.add(channel);
                    }
                    System.out.println("add:" + member);
                }
            }
            GroupListMessage groupListMessage = new GroupListMessage(groupChat.getId(),groupChat.getTitle(),groupChat.getInfo(),groupChat.getIcon(),groupChat.getChatId());
            groupListMessage.setProducers(members);
            groupListMessageList.add(groupListMessage);
            SessionUtil.bindChannelGroup(groupChatId,channelGroup);
        }
        return groupListMessageList;
    }
}
