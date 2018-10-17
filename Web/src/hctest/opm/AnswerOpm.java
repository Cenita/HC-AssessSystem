package hctest.opm;

import hctest.Dao.*;
import hctest.domain.*;
import hctest.dto.AnswerInfo;
import hctest.dto.PaperInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerOpm {

    //用户创建试卷，初始化试卷，开始答题
    public static boolean createAnswerPaper(User user,String paperid,String answerid) throws SQLException {
        //确保用户此前没有答过该份试卷
        Answer temp = AnswerDao.getAnswerByChoose(paperid,user.getId());
        if(temp!=null) return false;

        PaperInfo paperInfo = PaperOpm.getPaperInfoByPaperId(paperid);

        //创建答卷信息
        Answer answer = new Answer();
        answer.setPaperid(paperid);
        answer.setUserid(user.getId());
        answer.setId(answerid);

        AnswerDao.add(answer);

        //创建题目信息
        List<AnswerQuestion> aqList = new ArrayList<>();
        List<Question>qList = PaperOpm.getQuestionListByPaperid(paperid);
        int i=1;
        for(Question question:qList)
        {
            AnswerQuestion as = question.toAnswerQuestion();
            as.setAnswerid(answerid);
            as.setUserid(user.getId());
            as.setNumber(i++);
            aqList.add(as);
        }

        AnswerQuestionDao.addList(aqList);

        return true;
    }

    //用户暂存答题信息
    public static void updateUserAnswerQuestion(String answerid,String saveid,String uanswer) throws SQLException {

        //修改用户暂存信息
        AnswerQuestionDao.updateUserAnswerQuestion(uanswer,saveid);

        AnswerDao.updateAnswerTime(answerid);

    }

    public static AnswerInfo getAnswerInfoByAnswerId(User user,String answerid) throws SQLException {

        Answer answer = AnswerDao.getAnswerById(answerid);

        AnswerInfo answerInfo = new AnswerInfo(answer);

        List<AnswerQuestion> aqList = AnswerQuestionDao.getAllAnswerQuestionByChoose(user.getId(),answerid);

        answerInfo.setAnswerQuestionList(aqList);
        return  answerInfo;
    }

    public static AnswerInfo getAnswerInfoByChoose(User user,String paperid) throws SQLException {
        Answer answer = AnswerDao.getAnswerByChoose(paperid, user.getId());

        if (answer == null) return null;

        return AnswerOpm.getAnswerInfoByAnswerId(user, answer.getId());
    }

}
