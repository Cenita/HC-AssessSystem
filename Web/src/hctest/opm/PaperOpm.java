package hctest.opm;

import hctest.Dao.PaperDao;
import hctest.Dao.PaperQuestionDao;
import hctest.Dao.QuestionDao;
import hctest.domain.Paper;
import hctest.domain.PaperQuestion;
import hctest.domain.Question;
import hctest.dto.PaperInfo;
import hctest.dto.QuestionInfo;
import net.sf.json.JSONObject;
import org.apache.commons.collections4.list.TreeList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaperOpm {

    public static void deletePaperWithAll(String paperid) throws SQLException {

        PaperQuestionDao.deleteAllQuestionInPaper(paperid);
        PaperDao.deletePaper(paperid);
    }


    public static List<PaperInfo> getAllPaperWithInfo() throws SQLException {
        List<Paper> paperList = PaperDao.getAllPaper();

        List<PaperInfo>paperInfoList = new ArrayList<>();

        for (Paper paper:paperList)
        {
            List<String>questionIdList = PaperDao.getAllQuestionByPaper(paper.getId());

            System.out.println(questionIdList);

            paperInfoList.add(new PaperInfo(paper));
        }

        return paperInfoList;
    }

    public static PaperInfo getPaperInfoByPaperId(String paperid) throws SQLException {
        Paper paper = PaperDao.getPaperByPaperId(paperid);

        PaperInfo paperInfo = new PaperInfo(paper);

        List<Question>questions = getQuestionListByPaperid(paperid);
        paperInfo.setQuestionList(questions);

        return paperInfo;
    }

    //通过试卷id获取试卷题库集合
    public static List<Question> getQuestionListByPaperid(String paperid) throws SQLException {

        List<String>qSlist = PaperQuestionDao.getAllQuestionByPaperid(paperid);

        List<Question>questionList = new ArrayList<>();
        for(String questionid:qSlist)
        {
            Question t = QuestionDao.getQuestionByid(questionid);
            if(t==null) continue;
            questionList.add(t);
        }
        return questionList;
    }

    public static boolean addQuestionInPaper(String paperid,String questionid) throws SQLException {

        Paper paper = PaperDao.getPaperByPaperId(paperid);
        Question question = QuestionDao.getQuestionByid(questionid);

        if(question == null||paper==null) return false;

        List<PaperQuestion> pqList = PaperQuestionDao.getPaperQuestionByChoose(questionid,paperid);

        if(pqList.size()>0) return true;

        PaperQuestionDao.addPaperQuestion(questionid,paperid);
        return true;
    }

    public static JSONObject PaperToJson(Paper paper) throws SQLException {
        JSONObject jo = paper.toJson();
        jo.put("size",PaperDao.getPaperSizeByPaperId(paper.getId()));
        return jo;
    }
}
