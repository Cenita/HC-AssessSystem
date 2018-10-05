package hctest.service.filesystem;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "VisitFileSystemServlet",urlPatterns = {"/fileSystem","/file"})
public class VisitFileSystemServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        JSONObject jb = new JSONObject();
        Object login = session.getAttribute("login");
        if(login==null)
        {
            jb.put("status",400);
            jb.put("message","权限不足");
        }


        response.getWriter().write(jb.toString());
    }
}
