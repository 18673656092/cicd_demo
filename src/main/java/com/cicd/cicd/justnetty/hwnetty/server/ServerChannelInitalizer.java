package com.cicd.cicd.justnetty.hwnetty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by zhuran on 2018/7/5 0005
 */
public class ServerChannelInitalizer  extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline cp = socketChannel.pipeline();
        cp.addLast("decoder",new StringDecoder());
        cp.addLast("encoder",new StringEncoder());
        cp.addLast("handler",new ServiceHandler());
    }
}
