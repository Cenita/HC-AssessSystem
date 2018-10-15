package hctest.service.answer;

import hctest.base.LoginBaseServlet;
import hctest.domain.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AnswerServlet",urlPatterns = "/answer/")
public class AnswerServlet extends LoginBaseServlet{
    public void add(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {
        JSONObject jo = (JSONObject)request.getAttribute("jo");
        User user = (User)request.getAttribute("user");





    }

}
