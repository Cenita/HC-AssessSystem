package hctest.service;

import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.dto.RegisterUser;
import hctest.util.Config;
import hctest.util.FileUitl;
import hctest.util.HeaderUitl;
import hctest.util.MailUtil;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "RegisterServlet",
        urlPatterns = "/register"
)
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        Map<String,String[]>map = request.getParameterMap();
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        RegisterUser reuser = new RegisterUser();
        try {
            BeanUtils.populate(reuser,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object MailCode = session.getAttribute(Config.MailCode);
        Object MailAccount = session.getAttribute(Config.MailAccount);
        if(reuser.getEmail()==null||
                reuser.getUsername()==null ||
                reuser.getPassword()==null||
                reuser.getCode()==null||
                reuser.getTruename()==null||MailCode==null) {
            jo.put("status","600");
            jo.put("message","注册失败");
        }
        else
        {
            if(!reuser.getCode().toLowerCase().equals((String)MailCode)||!reuser.getEmail().equals((String)MailAccount))
            {
                jo.put("status","600");
                jo.put("message","验证码错误");
            }
            else
            {
                try {
                    UserDao.addUser(reuser.getUser());
                    String path = getServletContext().getRealPath("registerSuccessful.html");
                    String content = FileUitl.getFileToString(path);
                    MailUtil.sendMail(reuser.getEmail(),"欢迎使用环创答题系统",content);
                    jo.put("status","200");
                    jo.put("message","注册成功");
                } catch (SQLException e) {
                    e.printStackTrace();
                    jo.put("status","600");
                    jo.put("message","账户已存在或sql错误");
                } catch (Exception e) {
                    e.printStackTrace();
                    jo.put("status","200");
                    jo.put("message","注册成功");
                }
            }

        }

        response.getWriter().write(jo.toString());
    }
}
