package hctest.service.question;

import hctest.Dao.QuestionDao;
import hctest.domain.Question;
import hctest.util.HeaderUitl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "getQuestionServlet",urlPatterns = "/question/get")
public class getQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(response);

        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();

        Object aim = request.getParameter("aim");
        Object args = request.getParameter("args");

        if(aim==null||args==null)
        {
            jo.put("status",400);
            jo.put("message","参数非法");
        }
        else
        {
            if("all".equals((String)aim))
            {
                try {
                    List<Question> questions = QuestionDao.getAllQuestion();
                    jo.put("statis","200");
                    jo.put("message","查找成功");
                    jo.put("size",questions.size());
                    JSONObject questionlist = new JSONObject();
                    int i = 0;
                    for (Question qs:questions
                         ) {

                        questionlist.put(String.valueOf(i++),qs.toJSONObject());

                    }
                    jo.put("list",questionlist);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    jo.put("status","400");
//                    jo.put("message","sql查询失败");
                }catch (Exception e)
                {
                    jo.put("status","400");
                    jo.put("message","Ex查找失败");
                }
            }
            else if("id".equals((String)aim))
            {
                try {
                    Question question = QuestionDao.getQuestionByid((String)args);
                    jo.put("status","200");
                    jo.put("message","查找成功");
                    jo.put("question",question.toJSONObject());

                } catch (SQLException e) {
                    e.printStackTrace();
                    jo.put("status","400");
                    jo.put("message","查找失败");
                }catch (Exception e)
                {
                    jo.put("status","400");
                    jo.put("message","查找失败");
                }

            }
            else
            {
                jo.put("status","400");
                jo.put("message","查找方式错误");
            }
        }
        response.getWriter().write(jo.toString());
    }
}
