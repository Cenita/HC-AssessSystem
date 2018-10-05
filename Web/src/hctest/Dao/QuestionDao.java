package hctest.Dao;

import hctest.domain.Question;
import hctest.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class QuestionDao {


    public static void addQuestion(Question question) throws SQLException {
        String sql = "insert into question " +
                "(id,number,title,content,selection,answer,type,direction,grade,createtime,updatetime) " +
                "values (?,?,?,?,?,?,?,?,?,?,?)";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        Timestamp tm = new Timestamp(new Date().getTime());
        qr.update(sql,
                JdbcUtil.getUUID(),
                question.getNumber(),
                question.getTitle(),
                question.getContent(),
                question.getSelection(),
                question.getAnswer(),
                question.getType(),
                question.getDirection(),
                question.getGrade(),
                tm,tm);
    }

    public static void alterQuestionById(Question question) throws SQLException {
        String sql = "update question set " +
                "title = ? ," +
                "number = ? ," +
                "content = ? ," +
                "selection = ? ," +
                "answer = ? ," +
                "type = ? ," +
                "direction = ? ," +
                "grade = ? ," +
                "updatetime = ? " +
                "where id = ?";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        Timestamp ts = new Timestamp(new Date().getTime());
        qr.update(sql,
                question.getTitle(),question.getNumber(),question.getContent(),question.getSelection(),
                question.getAnswer(),question.getType(),question.getDirection(),question.getGrade(),ts,question.getId());

    }

    public static void deleteQuestionById(String questionId) throws SQLException {
        String sql = "delete from question where id = ?";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,questionId);
    }

    public static Question getQuestionByid(String questionId) throws SQLException {
        String sql = "select * from question where id = ?";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        Question question= qr.query(sql, new BeanHandler<Question>(Question.class),questionId);

        return question;
    }

    public static List<Question> getAllQuestion(){
        String sql = "select * from question";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        List<Question> questions = null;
        try {
            questions = (List<Question>) qr.query(sql, new BeanListHandler<Question>(Question.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }


}
