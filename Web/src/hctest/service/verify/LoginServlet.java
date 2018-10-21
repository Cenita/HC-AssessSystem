package hctest.service.verify;

import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;
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

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");

        Object VerifyCode = session.getAttribute(Config.VerifyCode);
        if(username==null||password==null||code==null||VerifyCode==null||!code.toLowerCase().equals((String)VerifyCode))
        {
            ReturnUtil.ToReturn(Status.Fail,"验证码错误",response);return;
        }

        try {
            User user = UserDao.getUserByUserName(username);
            if(user!=null&&user.getPassword().equals(password))
            {
                UserDao.updateUserUpdateTime(username);
                jo.put("user",user.toJson());
                jo.put(Config.Status,Status.Succeed);
                jo.put(Config.Message,"登录成功");
            }
            else
            {
                jo.put(Config.Status,Status.Fail);
                jo.put(Config.Message,"账户或密码错误");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            jo.put(Config.Status,Status.ServerFail);
            jo.put(Config.Message,"发生sql错误");
        }catch (Exception e)
        {
            jo.put(Config.Status,Status.Fail);
            jo.put(Config.Message,"空指针异常");
        }

        response.getWriter().write(jo.toString());
    }
}
