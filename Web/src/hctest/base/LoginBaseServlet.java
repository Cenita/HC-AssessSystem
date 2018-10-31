package hctest.base;

import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.util.Config;
import hctest.util.HeaderUitl;
import hctest.util.ReturnUtil;
import hctest.util.Status;
import net.sf.json.JSONObject;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Map;

public class LoginBaseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();

        Object login = session.getAttribute(Config.LoginID);
        String action = "";
        if(login==null) {ReturnUtil.ToReturn(Status.Warnning,"用户未登录",response);return;}
        try{
            User user = UserDao.getUserById((String)login);
            request.setAttribute(Config.User,user);

            Class clazz = this.getClass();
            if(!ServletFileUpload.isMultipartContent(request))
            {
                action = request.getParameter(Config.Action);
                if(action==null) return;
            }
            else
            {
                action = "upload";
            }
            Method method = clazz.getMethod(action,HttpServletRequest.class,HttpServletResponse.class);

            if(method!=null)
            {
                request.setAttribute("jo",jo);
                method.invoke(this,request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            jo.put(Config.Status, Status.ServerFail);
            jo.put(Config.Message,"发生sql错误");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            jo.put(Config.Status,Status.RequestFail);
            jo.put("message","没有找到该方法");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("status","500");
            jo.put("message","发生了未知错误");
        }
        response.getWriter().write(jo.toString());
    }
}
