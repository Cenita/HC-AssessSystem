package hctest.service.user;

import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.util.HeaderUitl;
import hctest.util.ReturnUtil;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "UserAlterServlet",urlPatterns = "/user/alter")
public class UserAlterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();

        Object login = session.getAttribute("login");

        if(login==null) return;

        Map<String,String[]> map = request.getParameterMap();
        User temp = new User();
        try {
            BeanUtils.populate(temp,map);
        } catch (Exception e) {
            e.printStackTrace();
            ReturnUtil.ToReturn("600","参数非法",response);return;
        }

        try {
            User user = UserDao.getUserById((String)login);

            user.setCollege(temp.getCollege());
            user.setGrade(temp.getGrade());
            user.setMotto(temp.getMotto());
            user.setTruename(temp.getTruename());
            user.setProfession(temp.getProfession());

            UserDao.updateUser(user);

            jo.put("status","200");
            jo.put("message","修改成功");
        } catch (SQLException e) {
            e.printStackTrace();
            jo.put("status","500");
            jo.put("message","发生了sql错误");
        }

        response.getWriter().write(jo.toString());
    }
}
