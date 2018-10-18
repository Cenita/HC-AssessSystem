package hctest.service.answer;

import hctest.Dao.AnswerDao;
import hctest.Dao.AnswerQuestionDao;
import hctest.base.LoginBaseServlet;
import hctest.domain.Answer;
import hctest.domain.AnswerQuestion;
import hctest.domain.User;
import hctest.dto.AnswerInfo;
import hctest.opm.AnswerOpm;
import hctest.util.Config;
import hctest.util.JdbcUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.util.List;
import java.util.jar.JarEntry;

@WebServlet(name = "AnswerServlet",urlPatterns = "/answer")
public class AnswerServlet extends LoginBaseServlet{
    public void add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject)request.getAttribute("jo");
        User user = (User)request.getAttribute(Config.User);
        String paperid = request.getParameter("paperid");
        if(paperid==null) return;
        String answerid = JdbcUtil.getUUID();
        boolean succeed = AnswerOpm.createAnswerPaper(user,paperid,answerid);
        if(succeed)
        {
            AnswerInfo answerInfo = AnswerOpm.getAnswerInfoByAnswerId(user,answerid);
            jo.put("answer",answerInfo.toJson());
            jo.put("status", "200");
            jo.put("message", "获取成功");
        }
        else {
            jo.put("status", "400");
            jo.put("message", "试卷已存在");
        }
    }
    public void save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
        JSONObject jo = (JSONObject)request.getAttribute("jo");
        User user = (User)request.getAttribute("user");
        String saveid = request.getParameter("saveid");
        String uanswer =request.getParameter("uanswer");
        String answerid = request.getParameter("answerid");
        if(uanswer==null||saveid==null||answerid==null) return;
        AnswerOpm.updateUserAnswerQuestion(answerid,saveid,uanswer);
        jo.put("status", "200");
        jo.put("message", "修改成功");
    }
    public void submit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
        JSONObject jo = (JSONObject)request.getAttribute("jo");
        User user = (User)request.getAttribute("user");

        String answerid = request.getParameter("answerid");
        if(answerid==null) return;
        AnswerDao.alterStatus(1,answerid);
        jo.put("status", "200");
        jo.put("message", "提交成功");
    }


    public void get(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
        JSONObject jo = (JSONObject)request.getAttribute("jo");
        User user = (User)request.getAttribute("user");
        String answerid = request.getParameter("answerid");
        if(answerid==null) return;

        AnswerInfo answerInfo = AnswerOpm.getAnswerInfoByAnswerId(user,answerid);
        jo.put("answer",answerInfo.toJson());
        jo.put("status", "200");
        jo.put("message", "获取成功");
    }

}
