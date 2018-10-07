package hctest.service.paper;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "AddPaperServlet",urlPatterns = "/paper/add")
public class AddPaperServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Map<String,String[]> map = request.getParameterMap();
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();

        response.getWriter().write("jasonpCallback("+jo.toString()+")");
    }
}
