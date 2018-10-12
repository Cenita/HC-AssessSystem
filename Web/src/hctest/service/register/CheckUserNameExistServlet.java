package hctest.service.register;

import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.util.HeaderUitl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CheckUserNameExistServlet",urlPatterns = "/register/isUserNameExist")
public class CheckUserNameExistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();

        String username = request.getParameter("username");

        if(username==null) return;

        try {
            User user = UserDao.getUserByUserName(username);

            if(user!=null)
            {
                jo.put("status","200");
                jo.put("message","存在");
            }
            else
            {
                jo.put("status","400");
                jo.put("message","不存在");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            jo.put("status","500");
            jo.put("message","发生sql错误");
        }


        response.getWriter().write(jo.toString());
    }
}
