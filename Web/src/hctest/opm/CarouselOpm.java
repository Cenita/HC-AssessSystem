package hctest.opm;

import com.sun.net.httpserver.HttpContext;
import hctest.Dao.CarouselDao;
import hctest.Dao.UserDao;
import hctest.domain.Carousel;
import hctest.util.FileUitl;
import hctest.util.JdbcUtil;
import hctest.util.PropertiesUitl;
import org.apache.tomcat.util.http.fileupload.FileItem;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class CarouselOpm {
    public static void addCarouse(FileItem item,ServletContext context) throws Exception {
        Carousel carousel = new Carousel();
        carousel.setId(JdbcUtil.getUUID());
        String Name = item.getName();
        String suffixname = Name.substring(Name.lastIndexOf("."));
        String fileName = carousel.getId()+suffixname;
        carousel.setTitle(Name);
        String saveDir = context.getRealPath(PropertiesUitl.getCarouselPath());
        String realurl = PropertiesUitl.getWebUrl()+PropertiesUitl.getCarouselPath()+fileName;

        carousel.setUrl(realurl);
        carousel.setContent("");

        InputStream in = item.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(saveDir+fileName);

        byte buffer[] = new byte[1024];

        int len=0;

        while ((len = in.read(buffer))>0 )
        {
            fileOutputStream.write(buffer,0,len);
        }

        fileOutputStream.close();
        in.close();
        item.delete();

        CarouselDao.add(carousel);
    }

    public static void deleteCarousel(String carouselid,ServletContext context) throws Exception {
        Carousel ca = CarouselDao.getCarouselById(carouselid);
        if(ca==null) return;
        String fileName = ca.getUrl().substring(ca.getUrl().lastIndexOf("/"));
        String dirName = context.getRealPath(PropertiesUitl.getCarouselPath());
        FileUitl.delFile(dirName,fileName);
        CarouselDao.delete(ca.getId());
    }
}
