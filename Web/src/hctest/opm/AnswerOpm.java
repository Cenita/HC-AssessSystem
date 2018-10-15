package hctest.opm;

import hctest.Dao.AnswerDao;
import hctest.Dao.PaperDao;
import hctest.Dao.PaperQuestionDao;
import hctest.domain.*;
import hctest.dto.PaperInfo;

import java.sql.SQLException;
import java.util.List;

public class AnswerOpm {

    //用户创建试卷，初始化试卷，开始答题
    public static boolean createAnswerPaper(User user,String paperid) throws SQLException {
        //确保用户此前没有答过该份试卷
        Answer temp = AnswerDao.getAnswerByChoose(paperid,user.getId());
        if(temp!=null) return false;

        PaperInfo paperInfo = PaperOpm.getPaperInfoByPaperId(paperid);

        //创建答卷信息
        Answer answer = new Answer();
        answer.setPaperid(paperid);
        answer.setUserid(user.getId());

        //创建题目信息



        return true;
    }

}
