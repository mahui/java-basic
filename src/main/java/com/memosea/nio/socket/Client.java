package com.memosea.nio.socket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

/**
 * Created by mahui on 2017/6/19.
 */
public class Client {
    static int port = 8080;
    static String ip = "localhost";
    static long timeout = 1000;
    static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(ip,port));
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT|SelectionKey.OP_READ|SelectionKey.OP_WRITE);


            while (true) {
                int readyChannels = selector.select(1000);
                if(readyChannels == 0){continue;}
                Set<SelectionKey> keySet = selector.selectedKeys();
                keySet.forEach(v->{
                    if(v.isConnectable()){
                        System.out.println("可连");
                    }else if(v.isReadable()){
                        System.out.println("可读");
                    }else if(v.isWritable()){
                        System.out.println("可写");
                        handleWrite(v);
                    }
                    keySet.remove(v);
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleRead(SelectionKey key){
        SocketChannel channel = (SocketChannel) key.channel();
        try {
            channel.read(byteBuffer);
            System.out.println(new String(byteBuffer.array(),0,byteBuffer.remaining()));
            byteBuffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void handleWrite(SelectionKey key){
        SocketChannel channel = (SocketChannel) key.channel();
        byteBuffer.clear();
        byteBuffer.put("test".getBytes());
        try {
            byteBuffer.flip();
            channel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
