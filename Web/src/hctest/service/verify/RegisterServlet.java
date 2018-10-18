package hctest.service.verify;

import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.util.Config;
import hctest.util.HeaderUitl;
import hctest.util.ReturnUtil;
import hctest.util.Status;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();

        String userString = request.getParameter(Config.User);
        String code = request.getParameter("code");
        Object MailCode = session.getAttribute(Config.MailCode);
        Object MailAccount = session.getAttribute(Config.MailAccount);
        User user = new User();
        JSONObject temp = JSONObject.fromObject(userString);
        user.setUsername(temp.getString("username"));
        user.setPassword(temp.getString("password"));
        user.setEmail(temp.getString("email"));
        user.setGrade(temp.getInt("grade"));
        user.setProfession(temp.getString("profession"));
        user.setCollege(temp.getString("college"));
        user.setTruename(temp.getString("truename"));
        user.setHeadimage("#");
        user.setMotto("HCTest");
        if(code==null||MailAccount==null||MailCode==null||userString==null||!code.toLowerCase().equals((String)MailCode)||!user.getEmail().equals((String)MailAccount))
        {
            ReturnUtil.ToReturn(Status.Fail,"验证码错误",response);return;
        }

        try {
            UserDao.addUser(user);
            jo.put(Config.Status,Status.Succeed);
            jo.put(Config.Message,"注册成功");
        } catch (SQLException e) {
            e.printStackTrace();
            jo.put(Config.Status,Status.ServerFail);
            jo.put(Config.Message,"发生了sql错误");
        }catch (Exception e)
        {
            e.printStackTrace();
            jo.put(Config.Status,Status.RequestFail);
            jo.put(Config.Message,"参数错误");
        }

        response.getWriter().write(jo.toString());
    }
}
