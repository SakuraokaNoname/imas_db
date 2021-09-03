package com.db.imas.server.handler;

import com.alibaba.fastjson.JSON;
import com.db.imas.model.entity.ImasGroupChat;
import com.db.imas.protocol.DataPacket;
import com.db.imas.protocol.packet.GroupListPacketResponse;
import com.db.imas.protocol.packet.GroupListResponse;
import com.db.imas.session.Producer;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.db.imas.util.Constans.PRODUCER_ONLINE;

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
        Map<String, List<GroupListResponse>> groupListResponseMap = getGroupList();
        GroupListPacketResponse response = new GroupListPacketResponse(groupListResponseMap);
        boolean flag = false;
        for(String key : groupChannelGroupMap.keySet()){
            if(!flag){
                ChannelGroup channelGroup = groupChannelGroupMap.get(key);
                Channel channel = channelGroup.find(ctx.channel().id());
                if(channel != null){
                    channelGroup.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(response)));
                    flag = true;
                }
            }
        }
    }

    public Map<String, List<GroupListResponse>> getGroupList(){
        Map<String,List<GroupListResponse>> groupListResponseMap = new HashMap<>();
        Map<String, ImasGroupChat> groupListMap = GroupUtil.getGroupListMap();
        List<GroupListResponse> groupListResponseList = new ArrayList<>();
        for(String key : groupListMap.keySet()){
            ImasGroupChat imasGroupChat = groupListMap.get(key);
            GroupListResponse groupListResponse = new GroupListResponse(imasGroupChat.getId(),imasGroupChat.getTitle(),imasGroupChat.getInfo(),imasGroupChat.getIcon(),imasGroupChat.getChatId());
            List<Producer> producers = new ArrayList<>();
            for(String member : imasGroupChat.getMember().split(",")){
                if(!StringUtils.isEmpty(member)){
                    Producer producer = new Producer();
                    producer.setId(Integer.parseInt(member));
                    Channel channel = SessionUtil.getChannel(member);
                    if(channel != null){
                        producer.setIsOnline(PRODUCER_ONLINE);
                        // TODO 添加进channelGroup
                    }
                    producers.add(producer);
                }
            }
            groupListResponse.setProducers(producers);
            groupListResponseList.add(groupListResponse);
        }
        groupListResponseMap.put("groupChat",groupListResponseList);
        return groupListResponseMap;
    }

}