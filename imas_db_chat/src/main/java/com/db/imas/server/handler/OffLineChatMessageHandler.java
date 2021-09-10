package com.db.imas.server.handler;

import com.alibaba.fastjson.JSON;
import com.db.imas.protocol.DataPacket;
import com.db.imas.protocol.packet.GroupChatResponse;
import com.db.imas.protocol.packet.OffGroupChatMessage;
import com.db.imas.protocol.packet.OffLineChatMessage;
import com.db.imas.service.GroupChatService;
import com.db.imas.util.ServiceUtil;
import com.db.imas.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author noname
 * @Date 2021/9/8 10:13
 * @Version 1.0
 * 推送离线信息
 */
public class OffLineChatMessageHandler extends SimpleChannelInboundHandler<DataPacket> {

    public static final OffLineChatMessageHandler INSTANCE = new OffLineChatMessageHandler();

    private OffLineChatMessageHandler() {
        groupChatService = ServiceUtil.getBean(GroupChatService.class);
    }

    private GroupChatService groupChatService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataPacket packet){
        System.out.println("推送离线信息");
        OffLineChatMessage message = JSON.parseObject(packet.getOriginalText(),OffLineChatMessage.class);
        System.out.println(packet.getOriginalText());
        Channel channel = SessionUtil.getChannel(message.getId());
        if(channel.isActive()){
            List<String> groupList = SessionUtil.hasGroupMember(channel);
            if(!CollectionUtils.isEmpty(groupList)){
                for(String str : groupList){
                    List<GroupChatResponse> responseList = groupChatService.getOffGroupChatMessageList(message.getId(),str);
                    if(!CollectionUtils.isEmpty(responseList)){
                        for(GroupChatResponse response : responseList){
                            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(response)));
                        }
                    }
                }
            }
        }
    }

}