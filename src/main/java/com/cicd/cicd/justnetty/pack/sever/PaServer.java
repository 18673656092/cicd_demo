package com.cicd.cicd.justnetty.pack.sever;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.EventListener;

/**
 * Created by zhuran on 2018/7/5 0005
 */
public class PaServer {
    private int port;
    public PaServer(int port){
        this.port = port;
    }
    public void run(){
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(boss,work).channel(NioServerSocketChannel.class).childHandler(new PaServerInitlater());
        try {
            ChannelFuture cf = b.bind(port).sync();
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        PaServer p = new PaServer(8080);
        p.run();
    }
}
