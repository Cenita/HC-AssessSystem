package hctest.service.carousel;

import hctest.Dao.CarouselDao;
import hctest.Dao.UserDao;
import hctest.base.LoginBaseServlet;
import hctest.domain.Carousel;
import hctest.domain.User;
import hctest.opm.CarouselOpm;
import hctest.util.Config;
import hctest.util.HeaderUitl;
import hctest.util.ReturnUtil;
import hctest.util.Status;
import net.sf.json.JSONObject;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CarouselServlet",urlPatterns = "/carousel/admin")
public class CarouselServlet extends LoginBaseServlet {
    public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        JSONObject jo = (JSONObject)request.getAttribute("jo");
        User user = (User)request.getAttribute(Config.User);

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setHeaderEncoding("utf-8");
        List<FileItem> fileItemList = null;
        try {
            fileItemList = upload.parseRequest(new ServletRequestContext(request));

            for (FileItem item:fileItemList)
            {
                if(!item.isFormField()&&item.getFieldName().equals("image"));
                {
                    CarouselOpm.addCarouse(item,getServletContext());
                    jo.put(Config.Status,Status.Succeed);
                    jo.put(Config.Message,"成功上传");
                    break;
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
            jo.put(Config.Message,"文件上传发生错误");
            jo.put(Config.Status,Status.RequestFail);
        } catch (Exception e) {
            e.printStackTrace();
            jo.put(Config.Message,"文件存储发生错误");
            jo.put(Config.Status,Status.ServerFail);
        }

    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject)request.getAttribute("jo");
        User user = (User)request.getAttribute(Config.User);

        List<Carousel> carouselList = CarouselDao.getAllCarousel();
        JSONObject list = new JSONObject();
        int i=0;
        for (Carousel ca:carouselList)
        {
            list.put(String.valueOf(i++),ca.toJson());
        }
        jo.put("size",carouselList.size());
        jo.put("carouselList",list);
        jo.put(Config.Status,Status.Succeed);
        jo.put(Config.Message,"获取成功");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jo = (JSONObject)request.getAttribute("jo");
        User user = (User)request.getAttribute(Config.User);

        String carouselid = request.getParameter("carouselid");
        if(carouselid==null) return;

        CarouselOpm.deleteCarousel(carouselid,getServletContext());
        jo.put(Config.Status,Status.Succeed);
        jo.put(Config.Message,"删除成功");
    }
}
