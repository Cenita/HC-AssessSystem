package hctest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUitl {

    public static String getFileToString(String path) throws Exception {
        String content;
        File file=new File(path);
        FileInputStream in=new FileInputStream(file);
        // size 为字串的长度 ，这里一次性读完
        int size=in.available();
        byte[] buffer=new byte[size];
        in.read(buffer);
        in.close();
        content=new String(buffer,"utf-8");
        return content;
    }
}
