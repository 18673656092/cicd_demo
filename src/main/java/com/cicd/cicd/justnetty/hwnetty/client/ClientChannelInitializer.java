package com.cicd.cicd.justnetty.hwnetty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * Created by zhuran on 2018/7/5 0005
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline cp = socketChannel.pipeline();
        cp.addLast("decoder",new StringDecoder());
        cp.addLast("encoder",new StringEncoder());
        cp.addLast("handler",new ClientHandler());
        cp.addLast("handler1",new BaseClientHandler());
    }
}
