package hctest.service.user;

import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.opm.UserOpm;
import hctest.util.HeaderUitl;
import hctest.util.JdbcUtil;
import hctest.util.PropertiesUitl;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserUploadHeadImageServlet",urlPatterns = "/user/upload")
public class UserUploadHeadImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();

        Object login = session.getAttribute("login");

        if(login==null) return;

        try {
            User user = UserDao.getUserById((String)login);


            if(!ServletFileUpload.isMultipartContent(request)) return;
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setHeaderEncoding("utf-8");
            List<FileItem> fileItemList = upload.parseRequest(new ServletRequestContext(request));

            for (FileItem item:fileItemList)
            {
                if(!item.isFormField()&&item.getFieldName().equals("headImage")) {
                    try
                    {
                        UserOpm.updateHeadImage(user,item,getServletContext());
                        jo.put("status","200");
                        jo.put("message","上传头像成功");
                    }catch (SQLException e)
                    {
                        e.printStackTrace();
                        jo.put("status","500");
                        jo.put("message","发生sql错误");
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                        jo.put("status","500");
                        jo.put("message","打开文件发生错误");
                    }
                    break;
                }
            }



        } catch (FileUploadException e) {
            e.printStackTrace();
            jo.put("status","600");
            jo.put("message","发生错误");
        }catch (SQLException e)
        {
            e.printStackTrace();
            jo.put("status","500");
            jo.put("message","发生sql错误");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("status","500");
            jo.put("message","发生文件错误");
        }

        response.getWriter().write(jo.toString());
    }
}
