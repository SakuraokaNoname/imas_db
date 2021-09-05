package com.db.imas.server.handler;

import com.alibaba.fastjson.JSON;
import com.db.imas.protocol.DataPacket;
import com.db.imas.protocol.packet.GroupChatMessage;
import com.db.imas.protocol.packet.GroupChatResponse;
import com.db.imas.service.GroupChatService;
import com.db.imas.util.DateUtil;
import com.db.imas.util.GroupUtil;
import com.db.imas.util.ServiceUtil;
import com.db.imas.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.db.imas.util.Constans.NO_RECEIVE;

/**
 * @Author noname
 * @Date 2021/8/28 17:44
 * @Version 1.0
 */
@ChannelHandler.Sharable
public class GroupChatHandler extends SimpleChannelInboundHandler<DataPacket> {

    private GroupChatService groupChatService;

    public static final GroupChatHandler INSTANCE = new GroupChatHandler();

    private GroupChatHandler() {
        groupChatService = ServiceUtil.getBean(GroupChatService.class);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataPacket packet){
        GroupChatMessage message = JSON.parseObject(packet.getOriginalText(),GroupChatMessage.class);
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(message.getToGroupId());
        GroupChatResponse response = new GroupChatResponse();

        response.setCreateTime(DateUtil.getNowTime());
        response.setMessage(message.getMessage());
        response.setToGroupId(message.getToGroupId());
        response.setSender(SessionUtil.getSession(SessionUtil.getChannel(message.getId() + "")));
        for(Channel channel:channelGroup){
            if(channel.isActive()){
                channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(response)));
            }
        }
        // TODO 如果channel不为空,且信息未读,则把缓存信息删除,并发送信息
        // TODO 如果channel为空,则存入缓存,等待用户上线进行发送
        List<String> offlineList = GroupUtil.getGroupOfflineList(message.getToGroupId());
        if(offlineList.size() > 0){
            for(String offId : offlineList){
                groupChatService.addCacheMessage(offId,response);
                System.out.println("信息存入缓存:" + JSON.toJSONString(response));
            }
        }
//        channelGroup.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(response)));
    }

}
