package Test01;

import java.io.File;
import java.io.IOException;

public class IO01 {



    public static void main(String[] args) throws IOException {
        File src = new File("IO_Test.txt");
        src.createNewFile();
        if(null == src || !src.exists()) {
            System.out.println("文件不存在");
        }else {
            if(src.isDirectory()) {
                System.out.println("文件夹");
            }else {
                System.out.println("文件");
            }
        }
    }

}
