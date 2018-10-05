package hctest.service.question;

import hctest.Dao.QuestionDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteQuestionServlet",urlPatterns = "/question/delete")
public class DeleteQuestionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();
        String questionId = request.getParameter("questionId");

        if(questionId==null)
        {
            jo.put("status","600");
            jo.put("message","非法操作");
        }
        else
        {
            try {
                QuestionDao.deleteQuestionById(questionId);
                jo.put("status","200");
                jo.put("message","删除成功");
            } catch (SQLException e) {
                jo.put("status","400");
                jo.put("message","删除失败");
            }
        }

        response.getWriter().write(jo.toString());
    }

}
