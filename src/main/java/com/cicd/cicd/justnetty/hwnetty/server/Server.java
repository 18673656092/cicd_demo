package com.cicd.cicd.justnetty.hwnetty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by zhuran on 2018/7/5 0005
 */
public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(boss, work).channel(NioServerSocketChannel.class).childHandler((new ServerChannelInitalizer()));
        try {
            ChannelFuture cf = sb.bind(port).sync();
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        Server s = new Server(8080);
        s.run();
    }
}
