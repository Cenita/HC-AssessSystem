package hctest.service.user;

import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.util.HeaderUitl;
import hctest.util.ReturnUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ChangeMailServlet",urlPatterns = "/user/changeMail")
public class ChangeMailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();

        Object login = session.getAttribute("login");

        if(login==null) return;

        try {
            User user = UserDao.getUserById((String)login);

            String mail = request.getParameter("mail");
            String code = request.getParameter("code");
            Object MailCode = session.getAttribute("MailCode");
            Object MailAccount = session.getAttribute("MailAccount");

            if(mail==null||code==null||MailAccount==null||MailCode==null)
            {
                ReturnUtil.ToReturn("600","更改失败",response);return;
            }

            if(!mail.equals((String)MailAccount)||!code.equals((String)MailCode))
            {
                ReturnUtil.ToReturn("400","验证码错误",response);return;
            }

            UserDao.updateUserEmail(mail,user.getId());

            jo.put("status","200");
            jo.put("message","更改成功");

        } catch (SQLException e) {
            e.printStackTrace();
            jo.put("status","500");
            jo.put("message","发生sql错误");
        }

        response.getWriter().write(jo.toString());
    }
}
