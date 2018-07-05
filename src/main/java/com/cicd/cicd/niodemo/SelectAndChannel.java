package com.cicd.cicd.niodemo;

import java.io.IOException;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhuran on 2018/7/5 0005
 */
public class SelectAndChannel {
    public static void main(String[] args) {
        //创建选择器
        Selector selector = null;
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SelectableChannel channel = null;
        try {
            channel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
//注册通道
        try {
            SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
        while(true) {
            //查看selector中的key是否准备好
            int readyChannels = 0;
            try {
                readyChannels = selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //小于0超时，等于0没准备好，大于0已经准备完毕
            if(readyChannels == 0) continue;
            //获取选择器中的key
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while(keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                //遍历已选择键集中的每个键，并检测各个键所对应的通道的就绪事件
                if(key.isAcceptable()) {
                    // 连接已经被ServerSocketChannel所接受
                } else if (key.isConnectable()) {
                    // 连接已经被远程终止.
                } else if (key.isReadable()) {
                    // 通道已经准备好读数据
                } else if (key.isWritable()) {
                    // 通道已经准备好写数据
                }
                keyIterator.remove();
            }
        }
    }
}
