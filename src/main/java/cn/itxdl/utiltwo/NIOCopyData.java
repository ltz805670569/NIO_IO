package cn.itxdl.utiltwo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOCopyData {
    private FileInputStream fis = null;
    private FileOutputStream fos = null;
    private FileChannel inChannel = null;
    private FileChannel outChannel = null;

    public void copyData(File src, File dst){
        try{
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dst);
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while(true){
                int i = inChannel.read(buffer);
                if(i == -1){
                    break;
                }
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
            outChannel.close();
            inChannel.close();
            fos.close();
            fis.close();
            System.out.println("复制完成");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        NIOCopyData ncd = new NIOCopyData();
        File src = new File("D:/ok.pdf");
        File dst = new File("E:/ok.pdf");
        ncd.copyData(src,dst);
    }
}
