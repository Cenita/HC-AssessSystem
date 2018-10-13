package hctest.service.paper;

import hctest.Dao.PaperQuestionDao;
import hctest.domain.PaperQuestion;
import hctest.opm.PaperOpm;
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

@WebServlet(name = "PaperAddQuestionServlet",urlPatterns = "/paper/question")
public class PaperAddQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();

        Object login = session.getAttribute("login");

        if(login==null) return;

        String method = request.getParameter("method");
        String paperid = request.getParameter("paperid");
        String[] questionidList = request.getParameterValues("questionid");

        if(method==null||paperid==null||questionidList.length==0) return;



        try {
            if(method.equals("add"))
            {
                for (String questionid:questionidList)
                {
                    PaperOpm.addQuestionInPaper(paperid,questionid);
                }
            }
            else if(method.equals("delete"))
            {
                for (String questionid:questionidList)
                {
                    PaperQuestionDao.deletePaperQuestion(questionid,paperid);
                }
            }
            else if(method.equals("equals"))
            {
                PaperQuestionDao.deleteAllQuestionInPaper(paperid);
                for (String questionid:questionidList)
                {
                    PaperOpm.addQuestionInPaper(paperid,questionid);
                }
            }
            jo.put("status","200");
            jo.put("message","操作成功");
        } catch (SQLException e) {
            e.printStackTrace();
            jo.put("status","500");
            jo.put("message","发生sql错误");
        }

        response.getWriter().write(jo.toString());
    }
}
