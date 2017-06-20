package com.memosea.nio.file;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by mahui on 2017/6/15.
 */
public class FileChannelReader {

    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/mahui/Documents/MyProj/vue-learn/day3/index.html","rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            int i = fileChannel.read(byteBuffer);
            while(i != -1){
                System.out.println("read size " + i);
                byteBuffer.flip();
//                while (byteBuffer.hasRemaining()){
                    byte[] dest = new byte[byteBuffer.remaining()];
                byteBuffer.hasRemaining();
                    byteBuffer.get(dest);
                    System.out.print(new String(dest,"utf-8"));
//                }
                byteBuffer.clear();
                i = fileChannel.read(byteBuffer);
            }
            fileChannel.close();
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
