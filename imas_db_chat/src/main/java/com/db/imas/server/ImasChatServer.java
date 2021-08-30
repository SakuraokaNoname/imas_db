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
 */
@Component
public class ImasChatServer {

    private static final int PORT = 7650;

    private NioEventLoopGroup boosGroup;
    private NioEventLoopGroup workerGroup;
    private final ServerBootstrap serverBootstrap;

    private static class  ImasChatWSServer {
        static final ImasChatServer instance = new ImasChatServer();
    }

    public static ImasChatServer getInstance() {
        return ImasChatWSServer.instance;
    }

    public ImasChatServer(){
        boosGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 512)
                .childOption(ChannelOption.TCP_NODELAY, true)
//                .childHandler(new ChannelInitializer<NioSocketChannel>() {
//                    protected void initChannel(NioSocketChannel ch) {
//                        ch.pipeline().addLast(new Spliter());
//                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
//                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
//                        ch.pipeline().addLast(AuthHandler.INSTANCE);
//                        ch.pipeline().addLast(IMHandler.INSTANCE);
//                    }
//                });
                .childHandler(new ImasChatWebSocketInitialzer());
        serverBootstrap.bind(PORT);
        System.out.println("netty服务启动成功");
    }
}
