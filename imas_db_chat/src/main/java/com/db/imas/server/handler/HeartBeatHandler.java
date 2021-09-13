package com.db.imas.server.handler;

import com.alibaba.fastjson.JSON;
import com.db.imas.protocol.packet.GroupListPacketResponse;
import com.db.imas.protocol.packet.GroupListResponse;
import com.db.imas.protocol.packet.HeartBeatMessage;
import com.db.imas.session.Session;
import com.db.imas.util.GroupUtil;
import com.db.imas.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.db.imas.protocol.command.Command.KEEPALIVE;

/**
 * @Author noname
 * @Date 2021/8/28 15:17
 * @Version 1.0
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 2;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);

        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if (SessionUtil.hasLogin(ctx.channel())) {
                //重连2次,则解除session绑定
                if(SessionUtil.getOverTime(ctx.channel()) <= 1){
                    HeartBeatMessage message = new HeartBeatMessage();
                    message.setCommand(KEEPALIVE);
                    message.setFlag(false);
                    ctx.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
                    SessionUtil.countOverTime(ctx.channel());
                    scheduleSendHeartBeat(ctx);
                }else{
                    // TODO 下线更新列表
                    Session session = SessionUtil.getSession(ctx.channel());
                    GroupUtil.removeGroupMember(session.getId() + "");
                    SessionUtil.unBindSession(ctx.channel());
                    ctx.channel().close();

                    Map<String, List<GroupListResponse>> groupListResponseMap = GroupUtil.refreshGroupMap();
                    GroupListPacketResponse response = new GroupListPacketResponse(groupListResponseMap);
                    Map<String, Channel> channelList = SessionUtil.getUserChannelMap();
                    for(String key : channelList.keySet()){
                        Channel channel = channelList.get(key);
                        if(channel.isActive()){
                            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(response)));
                        }
                    }
                }
            } else{

            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }

}
