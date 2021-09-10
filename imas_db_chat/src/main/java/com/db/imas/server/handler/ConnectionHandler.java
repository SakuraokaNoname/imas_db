package com.db.imas.server.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.db.imas.model.entity.ImasGroupChat;
import com.db.imas.protocol.DataPacket;
import com.db.imas.protocol.packet.ConnectionMessage;
import com.db.imas.protocol.packet.GroupListPacketResponse;
import com.db.imas.protocol.packet.GroupListResponse;
import com.db.imas.session.Producer;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.db.imas.protocol.command.Command.GROUP_LIST;
import static com.db.imas.util.Constans.PRODUCER_ONLINE;

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
        ConnectionMessage connectionMessage = JSON.parseObject(packet.getOriginalText(), ConnectionMessage.class);
        Channel channel = ctx.channel();
        if(SessionUtil.getChannel(connectionMessage.getId()) != null){
            Channel oldChannel = SessionUtil.getChannel(connectionMessage.getId());
            SessionUtil.unBindSession(oldChannel);
            oldChannel.close();
        }
        SessionUtil.bindSession(new Session(connectionMessage.getId(),connectionMessage.getName(),connectionMessage.getIcon(),connectionMessage.getChatId()), ctx.channel());

        SessionUtil.initOverTime(channel);
        Map<String,List<GroupListResponse>> groupListResponseMap = bindGroupChannel(ctx);
        GroupListPacketResponse response = new GroupListPacketResponse(groupListResponseMap);
        ctx.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(response)));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }

    public Map<String,List<GroupListResponse>> bindGroupChannel(ChannelHandlerContext ctx){
        Map<String,List<GroupListResponse>> groupListResponseMap = new HashMap<>();
        Map<String, ImasGroupChat> groupListMap = GroupUtil.getGroupListMap();
        List<GroupListResponse> groupListResponseList = new ArrayList<>();
        for(String key : groupListMap.keySet()){
            ChannelGroup channelGroup = new DefaultChannelGroup(key,ctx.executor());
            ImasGroupChat imasGroupChat = groupListMap.get(key);
            GroupListResponse groupListResponse = new GroupListResponse(imasGroupChat.getId(),imasGroupChat.getTitle(),imasGroupChat.getInfo(),imasGroupChat.getIcon(),imasGroupChat.getChatId());
            List<Producer> producers = new ArrayList<>();
            List<String> offlineList = new ArrayList<>();
            for(String member : imasGroupChat.getMember().split(",")){
                if(!StringUtils.isEmpty(member)){
                    Producer producer = new Producer();
                    producer.setId(Integer.parseInt(member));
                    Channel channel = SessionUtil.getChannel(member);
                    if(channel != null){
                        producer.setIsOnline(PRODUCER_ONLINE);
                        // TODO 添加进channelGroup
                        channelGroup.add(channel);
                    }else{
                        offlineList.add(member);
                    }
                    producers.add(producer);
                }
            }
            groupListResponse.setProducers(producers);
            groupListResponseList.add(groupListResponse);
            SessionUtil.bindChannelGroup(key,channelGroup);
            GroupUtil.initgroupOfflineMap(key,offlineList);
            System.out.println("离线人数:" + GroupUtil.getGroupOfflineList(key).size() + "---" + JSONObject.toJSONString(GroupUtil.getGroupOfflineList(key)));
        }

        groupListResponseMap.put("groupChat",groupListResponseList);
        return groupListResponseMap;
    }
}
