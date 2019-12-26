package cn.itxdl.utiltwo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChannelFileCopy {
    private FileInputStream fis = null;
    private FileOutputStream fos = null;
    private FileChannel inChannel = null;
    private FileChannel outChannel = null;

    public void copyFile(File src,File dst){
        if(src.isDirectory()){
            try {
                Path path = Paths.get(dst.getAbsolutePath());
                Files.createDirectory(path);
                File[] files = src.listFiles();
                for(File file : files){
                    System.out.println(file.getName());
                    copyFile(file,new File(dst,file.getName()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(src.isFile()){
            try {
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
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        File src = new File("D:/src");
        File dst = new File("E:/dst");
        ChannelFileCopy cfc = new ChannelFileCopy();
        cfc.copyFile(src,dst);
        System.out.println("复制完成");
    }
}
