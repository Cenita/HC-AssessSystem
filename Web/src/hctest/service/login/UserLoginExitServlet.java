package hctest.service.login;

import hctest.util.HeaderUitl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserLoginExitServlet",urlPatterns = "/login/exit")
public class UserLoginExitServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();


        session.removeAttribute("login");

        jo.put("message","不留痕迹");
        jo.put("status","200");

        response.getWriter().write(jo.toString());
    }
}
