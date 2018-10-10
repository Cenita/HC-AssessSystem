package hctest.opm;

import hctest.Dao.PaperDao;
import hctest.domain.Paper;
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
}
