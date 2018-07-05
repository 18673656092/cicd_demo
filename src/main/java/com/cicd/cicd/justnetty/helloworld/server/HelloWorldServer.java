package com.cicd.cicd.justnetty.helloworld.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by zhuran on 2018/7/4 0004
 */
public class HelloWorldServer {

    private int port;
    public HelloWorldServer(int port){
        this.port = port;
    }

    public void start(){

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(boss,worker).channel(NioServerSocketChannel.class).childHandler(new HelloWorldServerInitlalizer());
        try{
            ChannelFuture cf = sb.bind(port).sync();
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        HelloWorldServer h = new HelloWorldServer(8080);
        h.start();
    }
}
