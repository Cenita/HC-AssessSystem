package hctest.service.user;

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

@WebServlet(name = "LoginStatusServlet",urlPatterns = {"/login/status","/status"})
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
                jo.put("message","获取成功");
                jo.put("user",UserInfoUtil.getUserMessageToJson(user));
            } catch (SQLException e) {
                e.printStackTrace();
                jo.put("status","400");
                jo.put("message",".");
            }
        }
        response.getWriter().write(jo.toString());
    }
}
