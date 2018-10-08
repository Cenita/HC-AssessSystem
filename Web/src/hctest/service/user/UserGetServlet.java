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
import java.util.List;

@WebServlet(name = "UserGetServlet",urlPatterns = "/user/get")
public class UserGetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();

        String aim = request.getParameter("aim");
        String args = request.getParameter("args");

        if(aim==null||args==null)
        {
            jo.put("status",600);
            jo.put("message","参数非法");
        }
        else
        {
            if("all".equals(aim))
            {
                try {
                    List<User> users = UserDao.getAllUsers();

                    jo.put("size",users.size());
                    jo.put("status","200");
                    jo.put("message","获取成功");
                    JSONObject userlist = new JSONObject();
                    int i = 0;
                    for (User user:users) {
                            userlist.put(String.valueOf(i++), UserInfoUtil.getUserMessageToJson(user));
                    }
                    jo.put("list",userlist);

                } catch (SQLException e) {
                    e.printStackTrace();
                    jo.put("status","500");
                    jo.put("message","发生sql错误");
                }
            }
            else if("id".equals(aim))
            {
                try {
                    User user = UserDao.getUserById(args);
                    if(user==null)
                    {
                        jo.put("status",400);
                        jo.put("message","暂无目标信息");
                    }
                    else
                    {
                        jo.put("user",UserInfoUtil.getUserMessageToJson(user));
                        jo.put("status","200");
                        jo.put("message","查询成功");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    jo.put("status","500");
                    jo.put("message","发生sql错误");
                }
            }
        }

        response.getWriter().write(jo.toString());
    }
}
