package com.kang.nio.byteBuffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Title 类名
 * @Description 描述
 * @Date 2016/8/7.
 * @Author Healthy
 * @Version
 */
public class ByteBufferReader {

    public static void main(String[] args) {
        readAllocate();
        readWhile();
    }

    private static void readWhile() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream =  new FileInputStream("tomcat.txt");
            FileChannel fileChannel = fileInputStream.getChannel();
            ByteBuffer byteBuffer =  ByteBuffer.allocate(1024);
            while(fileChannel.read(byteBuffer) != -1){
                byteBuffer.flip();// 重设limit = position; position = 0; //设置为读模式
                while(byteBuffer.hasRemaining()){
                    System.out.println((char)byteBuffer.get());
                }
                byteBuffer.clear();//清缓存,position = 0; limit = capacity;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void readAllocate() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream =  new FileInputStream("tomcat.txt");
            FileChannel fileChannel = fileInputStream.getChannel();
            //ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//int a 1024 buffer;
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)fileChannel.size());//int a 1024 buffer;
            fileChannel.read(byteBuffer);
            byteBuffer.rewind();//将缓冲区当前位置position回复为0
            while(byteBuffer.hasRemaining()){
                System.out.println((char)byteBuffer.get());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
