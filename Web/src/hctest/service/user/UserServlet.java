package hctest.service.user;

import hctest.Dao.AnswerDao;
import hctest.Dao.UserDao;
import hctest.base.LoginBaseServlet;
import hctest.domain.Answer;
import hctest.domain.Paper;
import hctest.domain.User;
import hctest.dto.AnswerInfo;
import hctest.opm.AnswerOpm;
import hctest.opm.PaperOpm;
import hctest.util.Config;
import hctest.util.Status;
import net.sf.json.JSON;
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

@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends LoginBaseServlet {
    public void status(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject) request.getAttribute("jo");
        User user = (User)request.getAttribute("user");
        jo.put("user",user.toJson());
        jo.put(Config.Status, Status.Succeed);
        jo.put(Config.Message,"成功");
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject) request.getAttribute("jo");
        HttpSession session = request.getSession();
        session.removeAttribute(Config.LoginID);
        jo.put(Config.Status, Status.Succeed);
        jo.put(Config.Message,"成功离开");
    }

    public void changeMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject) request.getAttribute("jo");
        User user = (User)request.getAttribute(Config.User);
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        Object MailCode = session.getAttribute(Config.MailCode);
        Object MailAccount = session.getAttribute(Config.MailAccount);
        if(email==null||code==null||MailAccount==null||MailCode==null||!code.equals((String)MailCode)||!email.equals((String)MailAccount)){
            jo.put(Config.Status,Status.RequestFail);
            jo.put(Config.Message,"验证码错误");
            return;
        }

        UserDao.updateUserEmail(email,user.getId());
        jo.put(Config.Status,Status.Succeed);
        jo.put(Config.Message,"更新成功");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject) request.getAttribute("jo");
        User user = (User) request.getAttribute("user");
        String userString = request.getParameter(Config.User);

        if(userString==null) return;

        User u = new User();
        u.setId(user.getId());
        JSONObject temp = JSONObject.fromObject(userString);
        u.setCollege(temp.getString("college"));
        u.setTruename(temp.getString("truename"));
        u.setProfession(temp.getString("profession"));
        u.setMotto(temp.getString("motto"));
        u.setGrade(temp.getInt("grade"));
        UserDao.updateUser(user);
        jo.put(Config.Status,Status.Succeed);
        jo.put(Config.Message,"更新成功");
    }

    public static void getAnswerAndPaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject) request.getAttribute("jo");
        User user = (User) request.getAttribute("user");

        JSONObject answerJsonList = new JSONObject();
        JSONObject paperJsonList = new JSONObject();
        List<Answer>answerList = AnswerDao.getAllUserAnswer(user.getId());
        List<Paper>paperList = PaperOpm.getUserPaper(user);

        jo.put("paperSize",paperList.size());
        jo.put("answerSize",answerList.size());
        int i=0;
        for (Answer answer:answerList)
        {
            answerJsonList.put(String.valueOf(i++),answer.tojson());
        }
        int j=0;
        for(Paper paper:paperList)
        {
            paperJsonList.put(String.valueOf(j++),paper.toJson());
        }

        jo.put("answerList",answerJsonList);
        jo.put("paperList",paperJsonList);
        jo.put(Config.Status,Status.Succeed);
        jo.put(Config.Message,"获取成功");
    }

    public static void getAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject)request.getAttribute("jo");
        User user = (User)request.getAttribute("user");
        String answerid = request.getParameter("answerid");
        if(answerid==null) return;

        AnswerInfo answerInfo = AnswerOpm.getAnswerInfoByAnswerId(user.getId(),answerid);
        jo.put("answer",answerInfo.toJson());
        jo.put("status", "200");
        jo.put("message", "获取成功");
    }




}
