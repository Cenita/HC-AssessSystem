package hctest.Dao;

import hctest.domain.PaperQuestion;
import hctest.domain.Question;
import hctest.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaperQuestionDao {

    public static void addPaperQuestion(String questionid,String paperid) throws SQLException {
        String sql = "insert into paper_questions (id,questionid,paperid) values (?,?,?) ";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,JdbcUtil.getUUID(),questionid,paperid);
    }

    public static void deletePaperQuestion(String questionid,String paperid) throws SQLException {
        String sql = "delete from paper_questions where questionid=? and paperid = ? ";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,questionid,paperid);
    }

    public static List<PaperQuestion> getPaperQuestionByChoose(String questionid,String paperid) throws SQLException {
        String sql = "select * from paper_questions where questionid=? and paperid = ? ";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        return qr.query(sql,new BeanListHandler<PaperQuestion>(PaperQuestion.class),questionid,paperid);
    }

    public static void deleteAllQuestionInPaper(String paperid) throws SQLException {
        String sql = "delete from paper_questions where paperid = ? ";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,paperid);
    }

    public static List<String> getAllQuestionByPaperid(String paperid) throws SQLException {
        String sql = "select questionid from paper_questions where paperid = ? ";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        List<PaperQuestion>list = qr.query(sql,new BeanListHandler<PaperQuestion>(PaperQuestion.class),paperid);

        List<String> s = new ArrayList<>();
        for (PaperQuestion pq:list)
        {
            s.add(pq.getQuestionid());
            System.out.println(pq.getQuestionid());
        }

        return s;
    }
}
