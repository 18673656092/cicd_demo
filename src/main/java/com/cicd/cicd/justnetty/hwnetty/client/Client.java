package com.cicd.cicd.justnetty.hwnetty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by zhuran on 2018/7/5 0005
 */
public class Client {
    private String ip;
    private int port;
    public Client(String ip,int port){
        this.ip = ip;
        this.port = port;
    }
    public void run(){
        EventLoopGroup client = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(client).channel(NioSocketChannel.class).handler(new ClientChannelInitializer());
        try{
            ChannelFuture cf = b.connect(ip,port).sync();
            cf.channel().writeAndFlush("hello , I am client");
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            client.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        Client c = new Client("127.0.0.1",8080);
        c.run();
    }
}
