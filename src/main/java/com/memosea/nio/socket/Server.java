package com.memosea.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Created by mahui on 2017/6/19.
 */
public class Server {
    int port = 8080;
    int timeout = 1000;
    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));
            serverSocketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int readyChannel = selector.select();
                if(readyChannel == 0) continue;
                Set<SelectionKey> keySet = selector.selectedKeys();
                keySet.forEach(v->{
                    if(v.isReadable()){
                        handleRead(v);
                    }
                    keySet.remove(v);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void handleRead(SelectionKey key){
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            int readBytes = socketChannel.read(byteBuffer);
            System.out.println(new String(byteBuffer.array(),0,readBytes));
            byteBuffer.clear();
            key.interestOps(SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
