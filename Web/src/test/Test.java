package test;

import com.mysql.cj.xdevapi.JsonArray;
import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.opm.PaperOpm;
import hctest.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet(name = "test",
        urlPatterns = "/test"
)
public class Test extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);
        String user = request.getParameter("user");
        JSONObject js= JSONObject.fromObject(user);

        Iterator iter = js.keys();
        while (iter.hasNext())
        {
            System.out.println(iter.next().toString());
        }


        System.out.println(user);
        JSONObject jo = new JSONObject();
        jo.put("status","200");
        response.getWriter().write(jo.toString());
    }
}
