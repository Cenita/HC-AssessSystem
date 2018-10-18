package hctest.base;

import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.util.Config;
import hctest.util.HeaderUitl;
import hctest.util.Status;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

@WebServlet(name = "PostBaseServlet")
public class PostBaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();

        String action = request.getParameter(Config.Action);
        if(action==null) return;
        try {
            Class clazz = this.getClass();
            Method method = clazz.getMethod(action,HttpServletRequest.class,HttpServletResponse.class);

            if(method!=null)
            {
                request.setAttribute("jo",jo);
                method.invoke(this,request,response);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            jo.put(Config.Status,Status.RequestFail);
            jo.put(Config.Message,"没有找到该方法");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put(Config.Status,Status.RequestFail);
            jo.put(Config.Message,"数据获取失败");
        }
        response.getWriter().write(jo.toString());
    }
}
