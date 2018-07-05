package com.cicd.cicd.justnetty.pack.sever;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by zhuran on 2018/7/5 0005
 */
public class PaServerHandler extends ChannelInboundHandlerAdapter {
    private int counter;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server receive order : " + msg.toString() + ";the counter is: " + ++counter);
    }
}
