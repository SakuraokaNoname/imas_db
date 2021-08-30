/*
package com.db.imas.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.db.imas.protocol.DataPacket;
import com.db.imas.protocol.command.Command;
import com.db.imas.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

*/
/**
 * @Author noname
 * @Date 2021/8/28 15:16
 * @Version 1.0
 * <p>
 * 处理消息的handler
 * TextWebSocketFrame：在netty中，是用于websocket专门处理文本的对象，frame是消息的载体。
 *//*

public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //获取到所有的客户端channel。
    public static ChannelGroup groupRoom = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        //1.获取客户端发来的消息
        String content = msg.text();
        System.out.println(content);
        Channel currentChannel = ctx.channel();

        //2.判断消息类型，根据不同的类型来处理不同的业务
        DataPacket dataPacket = JSONObject.parseObject(content, DataPacket.class);
        Byte command = dataPacket.getCommand();

        if (command == Command.CONNECTION) {
            ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
            //  2.1 当websocket，第一次open的时候，初始化channel，把用的channel和userid关联起来
            String groupId = dataPacket.getGroupChatMessage().getProducerId();
            SessionUtil.bindChannelGroup(groupId,channelGroup);
            currentChannel.writeAndFlush(
                    new TextWebSocketFrame(
                            JSONObject.toJSONString(groupId)));
            System.out.println("加入netty连接");
        } else if(command == Command.ONE_CHAT){

        } else if (command == Command.GROUP_CHAT) {
            //  2.2 聊天类型的消息，把聊天记录保存到数据库中，同时标记消息的签收状态【未签收】
            GroupChatMessage groupChatMessage = dataPacket.getGroupChatMessage();
            String message = groupChatMessage.getMessage();
            String toGroupId = groupChatMessage.getToGroupId();
            String producerId = groupChatMessage.getProducerId();

            SessionUtil.getChannelGroup(toGroupId);
            currentChannel.writeAndFlush(
                    new TextWebSocketFrame(
                            JSONObject.toJSONString(message)));
            //保存消息到数据库，并且标记为未签收。
//            UserService userService = (UserService) SpringUtil.getBean("userService");
//            String msgId = userService.saveMsg(chatMsg);
//            chatMsg.setMsgId(msgId);

            //发送消息
            //从全局用户channel关系中获取接收方的channel
//            Channel receiverChannel = UserChannelRel.get(receiverId);
//            if (receiverChannel == null) {
//                //TODO 推送消息
//
//            } else {
//                //当receiverChannel不为空是，从channelGroup中查找对应的channel是否存在
//                Channel findChannel = users.find(receiverChannel.id());
//                if (findChannel != null) {
//                    //TODO 用户在线
//                    receiverChannel.writeAndFlush(
//                            new TextWebSocketFrame(
//                                    JSONObject.toJSONString(chatMsg)));
//                } else {
//                    //TODO 用户离线
//
//                }
//            }
        } else if (command == Command.KEEPALIVE) {
            //  2.4 心跳类型的消息
            System.out.println("收到来自channel为[" + currentChannel + "]的心跳包...");
        }
    }

}
*/
