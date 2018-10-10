package hctest.service.paper;

import hctest.Dao.PaperDao;
import hctest.Dao.UserDao;
import hctest.domain.Paper;
import hctest.domain.User;
import hctest.dto.PaperInfo;
import hctest.opm.PaperOpm;
import hctest.util.HeaderUitl;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "DeletePaperServlet",urlPatterns = "/paper/delete")
public class DeletePaperServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();

        Object login = session.getAttribute("login");
        if(login==null) return;

        try {
            User admin = UserDao.getUserById((String)login);

            String paperid = request.getParameter("paperid");

            if(paperid==null) return;

            PaperOpm.deletePaperWithAll(paperid);

            jo.put("status","200");
            jo.put("message","删除成功");

        } catch (SQLException e) {
            e.printStackTrace();
            jo.put("status","500");
            jo.put("message","发生sql错误");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("sattus","600");
            jo.put("message","参数非法");
        }


        response.getWriter().write(jo.toString());
    }
}
