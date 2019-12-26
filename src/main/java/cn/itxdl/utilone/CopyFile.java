package cn.itxdl.utilone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopyFile {


    public static void copyFile(File src,File dst){
        if(src.isDirectory()){
            dst.mkdir();
            File[] files = src.listFiles();
            for(File file : files){
                System.out.println(file.getName());
                copyFile(file,new File(dst,file.getName()));
            }
        }else if(src.isFile()){
            try {
                File file = new File(dst.getAbsolutePath());
                copyData(src.getAbsolutePath(),file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void copyData(String src, String dst) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dst);
            byte[] buffer = new byte[1024];
            int temp = 0;
            while((temp = fis.read(buffer)) != -1){
                fos.write(buffer,0,buffer.length);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
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
        copyFile(src,dst);
        System.out.println("复制完成");
    }
}
