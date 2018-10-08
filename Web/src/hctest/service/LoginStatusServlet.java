package hctest.service;

import hctest.Dao.UserDao;
import hctest.domain.User;
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

@WebServlet(name = "LoginStatusServlet",urlPatterns = "/status")
public class LoginStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);
        String realIp = HeaderUitl.getIpuser(request);

        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();
        Object login = session.getAttribute("login");
        jo.put("ip",realIp);
        if(login==null)
        {
            jo.put("status","400");
            jo.put("message",".");
        }
        else
        {
            try {
                User user = UserDao.getUserById((String)login);
                jo.put("status",200);
                jo.put("message","获取状态成功");
                JSONObject juser = new JSONObject();
                juser.put("username",user.getUsername());
                juser.put("motto",user.getMotto());
                juser.put("profession",user.getProfession());
                juser.put("college",user.getCollege());
                juser.put("permit", UserInfoUtil.getPremitMessage(user.getPermit()));
                juser.put("truename",user.getTruename());
                juser.put("grade",user.getGrade());
                juser.put("lasttime",user.getUpdatetime().toString());
                jo.put("user",juser.toString());
            } catch (SQLException e) {
                e.printStackTrace();
                jo.put("status","400");
                jo.put("message",".");
            }
        }
        response.getWriter().write(jo.toString());
    }
}
