package hctest.service;

import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.dto.VerifyCode;
import hctest.util.Config;
import hctest.util.GraphicHelper;
import hctest.util.HeaderUitl;
import hctest.util.UserInfoUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

@WebServlet(name = "LoginServlet",
        urlPatterns = "/login"
)
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
        Object VCode= session.getAttribute(Config.VerifyCode);
        if(
//                VCode==null||
                code==null
//                ||!code.toLowerCase().equals((String)VCode)
        )
        {
            jo.put("message","验证码错误");
            jo.put("status",400);
            VerifyCode refreshCode = GraphicHelper.randRomVerifyCode();
            session.setAttribute(Config.VerifyCode,refreshCode.getCode());
        }
        else
        {
            try {
                User user = UserDao.getUserByUserName(username);
                if(user==null||!user.getPassword().equals(password))
                {
                    jo.put("status",400);
                    jo.put("message","账号或密码不存在");
                }
                else
                {
                    UserDao.updateUserUpdateTime(username);
                    session.setAttribute("login",user.getId());
                    jo.put("status",200);
                    jo.put("message","登录成功");
                    jo.put("user",UserInfoUtil.getUserMessageToJson(user));

                }
            } catch (SQLException e) {
                jo.put("status",400);
                jo.put("message","服务器发生异常");
                e.printStackTrace();
            }
        }

        response.getWriter().write(jo.toString());
    }
}
