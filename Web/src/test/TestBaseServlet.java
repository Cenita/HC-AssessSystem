package test;

import hctest.base.LoginBaseServlet;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestBaseServlet",urlPatterns = "/testBase")
public class TestBaseServlet extends LoginBaseServlet {
    public void test(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {
        JSONObject jo = (JSONObject)request.getAttribute("jo");
        jo.put("status","200");
        jo.put("message","成功访问test");
    }

}
