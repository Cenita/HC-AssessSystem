package hctest.service.paper;

import hctest.Dao.PaperDao;
import hctest.Dao.UserDao;
import hctest.domain.Paper;
import hctest.domain.User;
import hctest.dto.PaperInfo;
import hctest.util.HeaderUitl;
import hctest.util.ReturnUtil;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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

@WebServlet(name = "AddPaperServlet",urlPatterns = "/paper/add")
public class AddPaperServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();

        Object login = session.getAttribute("login");
        if(login==null) return;
        try {
            User admin = UserDao.getUserById((String)login);

            Map<String,String[]> map = request.getParameterMap();
            System.out.println(request.getParameter("endtimeInt"));
            PaperInfo paperInfo = new PaperInfo();
            BeanUtils.populate(paperInfo,map);

            Paper paper = paperInfo.toPaper();

            System.out.println("传入但还没转化的时间搓:"+paperInfo.getStarttimeInt());

            PaperDao.addPaper(paper);

            jo.put("status","200");
            jo.put("message","增添成功");


        } catch (SQLException e) {
            e.printStackTrace();
            jo.put("status","500");
            jo.put("message","发生了sql错误");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("status","600");
            jo.put("message","参数错误");
        }


        response.getWriter().write(jo.toString());
    }
}
