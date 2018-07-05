package com.cicd.cicd.justnetty.helloworld.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zhuran on 2018/7/4 0004
 */
public class HelloWorldClient {
    private String ip;
    private int port;

    public HelloWorldClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() {
        EventLoopGroup client = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(client).channel(NioSocketChannel.class).handler(new HelloWorldChannelInitializer());
        try {
            Channel channel = b.connect(ip, port).sync().channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for(;;) {
                String msg = reader.readLine();
                if (msg == null) {
                    continue;
                }
                channel.writeAndFlush(msg + "\r\n");
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            client.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        HelloWorldClient h = new HelloWorldClient("127.0.0.1", 8080);
        h.start();
    }
}
