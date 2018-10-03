package hctest.service;

import hctest.Dao.UserDao;
import hctest.domain.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "LoginServlet",
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.setContentType("application/json;charset=utf-8");
        JSONObject jo = new JSONObject();

        try {
            User user = UserDao.getUserByUserName(username);
            if(user==null||!user.getPassword().equals(password))
            {
                jo.put("status",400);
                jo.put("message","账号或密码不存在");
            }
            else
            {
                jo.put("status",200);
                JSONObject juser = new JSONObject();
                juser.put("account",user.getUsername());
                juser.put("motto",user.getMotto());
                juser.put("profession",user.getProfession());
                juser.put("college",user.getCollege());

                jo.put("user",juser.toString());
                jo.put("message","登录成功");
            }

        } catch (SQLException e) {
            jo.put("status",400);
            e.printStackTrace();
        }
        response.getWriter().write(jo.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println();
    }
}
