package hctest.service.user.admin;

import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.dto.RegisterUser;
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

@WebServlet(name = "AdminChangePermitServlet",urlPatterns = "/changePermit")
public class AdminChangePermitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();

        Object login = session.getAttribute("login");
        if(login==null) return;


        String aim = request.getParameter("aim");
        String userid = request.getParameter("userid");

        if(aim==null||userid==null) return;

        try {
            User user = UserDao.getUserById(userid);
            int objectPermit = user.getPermit();

            if (aim.equals("up")) {
                if(objectPermit==9)
                {
                    ReturnUtil.ToReturn("400","已经是最高级",response);return;
                }
                UserDao.updateUserPermit(objectPermit+1,user.getId());
            } else if (aim.equals("down")) {
                if(objectPermit==0)
                {
                    ReturnUtil.ToReturn("400","已经是最低级",response);return;
                }
                UserDao.updateUserPermit(objectPermit-1,user.getId());
            } else if (aim.equals("equal")) {
                String permit = request.getParameter("permit");
                if(permit==null||(Integer.valueOf(permit)<0||Integer.valueOf(permit)>9))
                {
                    jo.put("status","600");
                    jo.put("status","参数非法");
                }
                else
                {
                    UserDao.updateUserPermit(Integer.valueOf(permit),user.getId());
                }

            } else {
                ReturnUtil.ToReturn("600","参数非法",response);
                return;
            }
            jo.put("status","200");
            jo.put("message","修改成功");
        } catch (SQLException e) {
            e.printStackTrace();
            jo.put("status","500");
            jo.put("message","sql错误");
        }catch (Exception e) {
            e.printStackTrace();
            jo.put("status","400");
            jo.put("message","用户不存在");
        }

        response.getWriter().write(jo.toString());
    }
}
