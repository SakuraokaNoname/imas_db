package com.db.imas.server.handler;

import com.alibaba.fastjson.JSON;
import com.db.imas.protocol.DataPacket;
import com.db.imas.protocol.packet.GroupListPacketResponse;
import com.db.imas.protocol.packet.GroupListResponse;
import com.db.imas.util.GroupUtil;
import com.db.imas.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author noname
 * @Date 2021/9/3 15:08
 * @Version 1.0
 */
public class BeOnlineHandler extends SimpleChannelInboundHandler<DataPacket> {

    public static final BeOnlineHandler INSTANCE = new BeOnlineHandler();

    private BeOnlineHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataPacket packet){
        Map<String, ChannelGroup> groupChannelGroupMap = SessionUtil.getGroupChannelGroupMap();
        Map<String, List<GroupListResponse>> groupListResponseMap = GroupUtil.refreshGroupMap();
        GroupListPacketResponse response = new GroupListPacketResponse(groupListResponseMap);
        List<Channel> channelList = new ArrayList<>();
        for(String key : groupChannelGroupMap.keySet()){
            ChannelGroup channelGroup = groupChannelGroupMap.get(key);
            for(Channel channel : channelGroup){
                channelList.add(channel);
            }
        }
        for(Channel channel : channelList){
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(response)));
        }
    }
}