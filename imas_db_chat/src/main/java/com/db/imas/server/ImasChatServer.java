package com.db.imas.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * @Author noname
 * @Date 2021/8/17 16:29
 * @Version 1.0
 *
 * Netty服务器启动类
 */
@Component
public class ImasChatServer {

    private static final int PORT = 7650;

    private NioEventLoopGroup boosGroup;
    private NioEventLoopGroup workerGroup;
    private final ServerBootstrap serverBootstrap;

    private static class ImasChatWSServer {
        static final ImasChatServer instance = new ImasChatServer();
    }

    public static ImasChatServer getInstance() {
        return ImasChatWSServer.instance;
    }

    public ImasChatServer(){
        boosGroup = new NioEventLoopGroup(); //负责创建新连接
        workerGroup = new NioEventLoopGroup(); //负责读取数据以及业务逻辑处理
        serverBootstrap = new ServerBootstrap(); //负责引导进行服务端的启动工作
        serverBootstrap
                .group(boosGroup, workerGroup) //配置线程组
                .channel(NioServerSocketChannel.class) //设置IO模型
                .option(ChannelOption.SO_BACKLOG, 512) //设置父连接的额外属性
                .childOption(ChannelOption.TCP_NODELAY, true) //设置每条子连接的额外属性,是否开启Nagle算法
                .childHandler(new ImasChatWebSocketInitialzer()); //为每个子连接初始化管道处理器
//              .childHandler(new ChannelInitializer<NioSocketChannel>() {
//                    protected void initChannel(NioSocketChannel ch) {
//                        ch.pipeline().addLast(new Spliter());
//                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
//                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
//                        ch.pipeline().addLast(AuthHandler.INSTANCE);
//                        ch.pipeline().addLast(IMHandler.INSTANCE);
//                    }
//                });
        serverBootstrap.bind(PORT); //绑定端口号
    }
}
