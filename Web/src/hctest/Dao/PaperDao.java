package hctest.Dao;

import hctest.domain.Paper;
import hctest.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class PaperDao {

    public static void addPaper(Paper paper) throws SQLException {
        String sql = "insert into paper " +
                "(id,title,number,grade,permit,direction,createtime,updatetime,starttime,endtime) " +
                "values (?,?,?,?,?,?,?,?,?,?)";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        Timestamp ts = new Timestamp(new Date().getTime());
        Timestamp starttime = new Timestamp(paper.getStarttime().getTime());
        Timestamp endtime = new Timestamp(paper.getEndtime().getTime());

        qr.update(sql,
                JdbcUtil.getUUID(), paper.getTitle(), paper.getNumber(), paper.getGrade(), paper.getPermit(),
                paper.getDirection(), ts, ts,starttime ,endtime );
    }

    public static void updatePaper(Paper paper) throws SQLException {
        String sql = "update paper set " +
                "title = ?" +
                "number = ? " +
                "grade = ? " +
                "permit = ? " +
                "direction = ? " +
                "starttime = ? " +
                "endtime = ? " +
                "updatetime = ? " +
                "where id = ? ";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        Timestamp ts = new Timestamp(new Date().getTime());

        qr.update(sql, paper.getTitle(), paper.getNumber(), paper.getGrade(), paper.getPermit(),
                paper.getDirection(), paper.getStarttime(), paper.getEndtime(), ts, paper.getId());
    }

    public static void deletePaper(String paperId) throws SQLException {

        deleteAllQuestionToPaper(paperId);

        String sql = "delete from paper where paperid = ?";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,paperId);

    }

    public static void addQuestionToPaper(String paperid, String questionid) throws SQLException {

        PaperDao.deleteQuestionToPaper(paperid,questionid);

        String sql = "insert into paper_questions values (?,?,?)";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,JdbcUtil.getUUID(),questionid,paperid);

    }

    public static List<String> getAllQuestionByPaper(String paperid) throws SQLException {
        String sql = "select questionid from paper_questions where paperid = ?";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        List<String> list =  qr.query(sql, new BeanListHandler<String>(String.class),paperid);

        return list;
    }

    public static void deleteQuestionToPaper(String paperid,String questionid) throws SQLException {

        String sql = "delete from paper_questions where paperid = ? and questionid = ?";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,paperid,questionid);

    }

    public static void deleteAllQuestionToPaper(String paperid) throws SQLException {
        String sql = "delete from paper_questions where paperid = ?";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,paperid);
    }

    public static List<Paper> getAllPaper() throws SQLException {
        String sql = "select * from paper";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        return qr.query(sql,new BeanListHandler<Paper>(Paper.class));
    }

    public static Paper getPaperByPaperId(String paperid) throws SQLException {
        String sql = "select * from paper where id = ? ";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        return qr.query(sql,new BeanHandler<Paper>(Paper.class),paperid);
    }

    public static long getPaperSizeByPaperId(String id) throws SQLException {

        String sql = "select count(*) from paper_questions where paperid= ?";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        return qr.query(sql,new ScalarHandler<>(),id);
    }
}