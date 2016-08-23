package com.kang.nio.fileChannel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
public class FileChannelWriter {

    public static void main(String[] args) {
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannel = null;
        try {
            fileOutputStream = new FileOutputStream("tomcatWirter.txt");
            fileChannel = fileOutputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            String writerContext = "content is tom!";
            for(int i = 0;i<writerContext.length();i++){
                byteBuffer.putChar(writerContext.charAt(i));
            }
            byteBuffer.flip();// limit = position; position = 0;
            fileChannel.write(byteBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
