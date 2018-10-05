package hctest.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

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

    public static String getImageBinary(BufferedImage bi,String imageType) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //经测试转换的图片是格式这里就什么格式，否则会失真//
        ImageIO.write(bi, imageType, baos);
        byte[] bytes = baos.toByteArray();
        return new String(bytes);
//        return Base64.encodeBase64URLSafeString(bytes);
    }

}
