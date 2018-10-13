package hctest.opm;

import hctest.Dao.PaperDao;
import hctest.Dao.PaperQuestionDao;
import hctest.Dao.QuestionDao;
import hctest.domain.Paper;
import hctest.domain.PaperQuestion;
import hctest.domain.Question;
import hctest.dto.PaperInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaperOpm {

    public static void deletePaperWithAll(String paperid) throws SQLException {
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

        return new PaperInfo(paper);
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
}
