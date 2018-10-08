package hctest.service.user;

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

@WebServlet(name = "UserChangePasswordServlet",urlPatterns = "/user/changePassword")
public class UserChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();

        Object login = session.getAttribute("login");
        String code = request.getParameter("code");
        if(login==null||code==null) return;

        try {
            User user = UserDao.getUserById((String) login);


            String aim = request.getParameter("aim");
            String newPassword = request.getParameter("newPassword");
            if (aim == null || newPassword == null) {
                jo.put("status", "600");
                jo.put("message", "参数非法");
            } else {
                if ("normal".equals(aim)) {
                    Object VCode = session.getAttribute("VCode");
                    String password = request.getParameter("password");
                    if (password!=null&&VCode != null && code.toLowerCase().equals((String) VCode)) {

                        if(user.getPassword().equals(password))
                        {
                            try {
                                UserDao.updateUserPassword(newPassword,user.getId());
                                jo.put("status","200");
                                jo.put("message","修改密码成功");
                            }catch (SQLException e)
                            {
                                jo.put("status","500");
                                jo.put("message","发生sql错误");
                            }
                        }
                        else
                        {
                            jo.put("status","400");
                            jo.put("message","密码错误");
                        }

                    } else {
                        jo.put("status", "600");
                        jo.put("message", "验证码不正确");
                    }

                }else if("forget".equals(aim))
                {
                    Object MailCode = session.getAttribute("MailCode");
                    if(MailCode!=null&&code.toLowerCase().equals((String)MailCode))
                    {
                        try{
                            UserDao.updateUserPassword(newPassword,user.getId());
                            jo.put("status","200");
                            jo.put("message","修改密码成功");

                        }catch (SQLException e)
                        {
                            jo.put("status","500");
                            jo.put("message","发生sql错误");
                        }
                    }
                    else
                    {
                        jo.put("status","400");
                        jo.put("message","密码错误");
                    }
                }
            }


        }catch (SQLException e)
        {
            e.printStackTrace();
            jo.put("status","500");
            jo.put("message","用户非法");
        }

        response.getWriter().write(jo.toString());
    }
}
