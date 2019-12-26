package cn.itxdl.utiltwo;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewCopyFile {


    public static void copyFile(File src, File dst){
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
                Path path = Paths.get(src.getAbsolutePath());
                File file = new File(dst.getAbsolutePath());
//                file.createNewFile();
                Files.copy(path,new FileOutputStream(file));
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
