package hctest.service;

import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.util.FileUitl;
import hctest.util.MailUtil;
import net.sf.json.JSONObject;
import org.apache.catalina.mbeans.MBeanUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "RegisterServlet",
        urlPatterns = "/register"
)
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setContentType("application/json;charset=utf-8");
        Map<String,String[]>map = request.getParameterMap();
        JSONObject jo = new JSONObject();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(user.getEmail()==null||user.getUsername()==null||user.getPassword()==null)
        {
            jo.put("status","400");
        }
        else
        {
            try {
                UserDao.addUser(user);
                String path = getServletContext().getRealPath("registerlink.html");
                String content = FileUitl.getFileToString(path);
                MailUtil.sendMail(user.getEmail(),"欢迎使用环创答题系统",content);
                jo.put("message","邮件发送成功");
                jo.put("status","200");
            } catch (SQLException e) {
                e.printStackTrace();
                jo.put("status","400");
            } catch (Exception e) {
                jo.put("status","200");
                jo.put("message","邮件发送失败");
            }
        }

        response.getWriter().write(jo.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
