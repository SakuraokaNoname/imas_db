package com.db.imas.server;

import com.db.imas.server.handler.HeartBeatHandler;
import com.db.imas.server.handler.MainHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author noname
 * @Date 2021/8/28 15:11
 * @Version 1.0
 *
 * 管道处理器?
 */
public class ImasChatWebSocketInitialzer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {

        // ChannelPipeline类是ChannelHandler实例对象的链表，用于处理或截获通道的接收和发送数据。
        // 它提供了一种高级的截取过滤模式（类似serverlet中的filter功能），
        // 让用户可以在ChannelPipeline中完全控制一个事件以及如何处理ChannelHandler与ChannelPipeline的交互。
        ChannelPipeline pipeline = nioSocketChannel.pipeline();
        //======================== http相关=============================
        //websocket基于http协议，所以需要HttpServerCodec
        pipeline.addLast("HttpServerCodec",new HttpServerCodec());

        //对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());

        //对httpMessage进行聚合，聚合成AggregatedFullHttpRequest和AggregatedFullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));


        // ====================== 增加心跳支持 start    ======================
        // 针对客户端，如果在1分钟时没有向服务端发送读写心跳(ALL)，则主动断开
        // 如果是读空闲或者写空闲，不处理
        // pipeline.addLast(new IdleStateHandler(8, 10, 12));
        // 自定义的空闲状态检测
        pipeline.addLast(new HeartBeatHandler());
        // ====================== 增加心跳支持 end    ======================

        //======================== websocket相关=============================
        //websocket服务器处理的协议，用于指定给客户端连接访问的路由："/ws"
        //本handler会帮你处理一些繁重的复杂的事。会帮你处理握手动作: handshaking
        //对于websocket来讲，都是以frames进行传输的，不同的数据类型对应的frames也不同。
        pipeline.addLast(new WebSocketServerProtocolHandler("/imaschat"));

        // 自定义的全局处理器
        pipeline.addLast(MainHandler.INSTANCE);

    }

}
