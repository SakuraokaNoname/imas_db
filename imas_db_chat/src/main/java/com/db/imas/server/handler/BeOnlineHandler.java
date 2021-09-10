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
        System.out.println("用户上线");
        Map<String, ChannelGroup> groupChannelGroupMap = SessionUtil.getGroupChannelGroupMap();
        Map<String, List<GroupListResponse>> groupListResponseMap = GroupUtil.refreshGroupMap();
        GroupListPacketResponse response = new GroupListPacketResponse(groupListResponseMap);
        boolean flag = false;
        for(String key : groupChannelGroupMap.keySet()){
            if(!flag){
                ChannelGroup channelGroup = groupChannelGroupMap.get(key);
                Channel channel = channelGroup.find(ctx.channel().id());
                if(channel != null && channel != ctx.channel()){
                    channelGroup.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(response)));
                    flag = true;
                }
            }
        }
    }
}