package hctest.service.question;

import hctest.Dao.QuestionDao;
import hctest.base.LoginBaseServlet;
import hctest.domain.Question;
import hctest.util.Config;
import hctest.util.Status;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "QuestionServlet",urlPatterns = "/question")
public class QuestionServlet extends LoginBaseServlet {
    public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject)request.getAttribute("jo");

        String questionid = request.getParameter("questionid");
        if(questionid==null) return;

        Question question = QuestionDao.getQuestionByid(questionid);
        jo.put(Config.Question,question.toJson());
        jo.put(Config.Status, Status.Succeed);
        jo.put(Config.Message,"获取成功");
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject)request.getAttribute("jo");

        List<Question>qList = QuestionDao.getAllQuestion();
        JSONObject questionList = new JSONObject();
        int i = 0;
        for (Question question:qList)
        {
            questionList.put(String.valueOf(i++),question.toJson());
        }
        jo.put("size",qList.size());
        jo.put("questionList",questionList);
        jo.put(Config.Status, Status.Succeed);
        jo.put(Config.Message,"获取成功");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject)request.getAttribute("jo");

        String questionid = request.getParameter("questionid");
        if(questionid==null) return;

        QuestionDao.deleteQuestionById("id");
        jo.put(Config.Status, Status.Succeed);
        jo.put(Config.Message,"删除成功");
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject)request.getAttribute("jo");

        String questionString = request.getParameter(Config.Question);
        if(questionString==null) return;

        Question question = new Question();
        JSONObject temp = JSONObject.fromObject(questionString);
        question.setNumber(String.valueOf(new Date().getTime()));
        question.setAnswer(temp.getString("answer"));
        question.setContent(temp.getString("content"));
        question.setDirection(temp.getString("direction"));
        question.setTitle(temp.getString("title"));
        question.setGrade(temp.getInt("grade"));
        question.setType(temp.getInt("type"));
        question.setSelection(temp.getString("selection"));

        QuestionDao.addQuestion(question);
        jo.put(Config.Status, Status.Succeed);
        jo.put(Config.Message,"添加成功");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject)request.getAttribute("jo");

        String questionString = request.getParameter(Config.Question);
        if(questionString==null) return;

        Question question = new Question();
        JSONObject temp = JSONObject.fromObject(questionString);
        question.setNumber(String.valueOf(new Date().getTime()));
        question.setAnswer(temp.getString("answer"));
        question.setContent(temp.getString("content"));
        question.setDirection(temp.getString("direction"));
        question.setTitle(temp.getString("title"));
        question.setGrade(temp.getInt("grade"));
        question.setType(temp.getInt("type"));
        question.setSelection(temp.getString("selection"));
        question.setId(temp.getString("id"));

        QuestionDao.alterQuestionById(question);
        jo.put(Config.Status, Status.Succeed);
        jo.put(Config.Message,"更新成功");
    }
}
