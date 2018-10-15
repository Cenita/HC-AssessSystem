package hctest.Dao;

import hctest.domain.Answer;
import hctest.domain.AnswerQuestion;
import hctest.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AnswerDao {

    public static void add(Answer answer) throws SQLException {
        String sql = "insert into answer (id,paperid,userid,status,createtime,updatetime) values(?,?,?,?,?,?)";

        Timestamp ts = new Timestamp(new Date().getTime());

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,JdbcUtil.getUUID(),answer.getPaperid(),answer.getUserid(),0,ts,ts);

    }

    public static void alterStatus(int status,String id) throws SQLException {
        String sql = "update answer set status = ? ,updatetime = ? where id = ? ";
        Timestamp ts = new Timestamp(new Date().getTime());

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,status,ts,id);

    }

    public static void deleteByChoose(String paperid,String userid) throws SQLException {
        String sql = "delete from answer where paperid=? and userid = ?";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        qr.update(sql,paperid,userid);
    }

    public static Answer getAnswerByChoose(String paperid,String userid) throws SQLException {
        String sql = "select * from answer where paperid=? and userid = ?";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        return qr.query(sql,new BeanHandler<Answer>(Answer.class),paperid,userid);
    }

    public static List<Answer> getAllAnswer() throws SQLException {
        String sql = "select * from answer";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        return qr.query(sql,new BeanListHandler<Answer>(Answer.class));
    }

    public static List<Answer> getAllUserAnswer(String userid) throws SQLException {
        String sql = "select * from answer where userid = ?";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        return qr.query(sql,new BeanListHandler<Answer>(Answer.class),userid);
    }

    public static void addAnswerQuestion(AnswerQuestion answerQuestion) throws SQLException {
        String sql = "insert into answer_questions (id,answerid,number,content,type,selection,answer,score,userid) values " +
                "(?,?,?,?,?,?,?,?,?)";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        qr.update(sql,answerQuestion.getId(),
                answerQuestion.getAnswerid(),
                answerQuestion.getNumber(),
                answerQuestion.getContent(),
                answerQuestion.getType(),
                answerQuestion.getSelection(),
                answerQuestion.getAnswer(),
                -1,
                answerQuestion.getUserid());
    }

    //设置答题的分数
    public static void setAnswerQuestionScore(int score,AnswerQuestion aq) throws SQLException {
        String sql = "update answer_questions set score = ? where id = ? ";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        qr.update(sql,score,aq.getId());
    }

    //获取答卷的全部题目
    public static List<AnswerQuestion> getAnswerQuestionByChoose(String answerid,String userid) throws SQLException {
        String sql = "select * from answer_questions where answerid=? and userid=?";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        return qr.query(sql,new BeanListHandler<AnswerQuestion>(AnswerQuestion.class),answerid,userid);
    }



}
