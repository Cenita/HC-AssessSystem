package hctest.service.question;

import hctest.Dao.QuestionDao;
import hctest.domain.Question;
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
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "AddQuestionServlet",urlPatterns = "/question/add")
public class AddQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(response);

        Map<String,String[]> map = request.getParameterMap();
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();

        Question question = new Question();
        try {
            BeanUtils.populate(question,map);
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("status","600");
            jo.put("message","参数接收失败");
        }
        try {
            QuestionDao.alterQuestionById(question);
            jo.put("status","200");
            jo.put("message","修改题目成功");
        } catch (SQLException e) {
            e.printStackTrace();
            jo.put("status","400");
            jo.put("message","修改失败");
        }
        response.getWriter().write(jo.toString());
    }
}
