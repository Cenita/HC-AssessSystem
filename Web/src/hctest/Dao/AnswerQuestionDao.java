package hctest.Dao;

import hctest.domain.AnswerQuestion;
import hctest.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AnswerQuestionDao {

    public static void addList(List<AnswerQuestion> aqList) throws SQLException {
        for(AnswerQuestion aq : aqList)
        {
            add(aq);
        }
    }

    public static void add(AnswerQuestion aq) throws SQLException {
        String sql = "insert into answer_questions (id,answerid,userid,number,content,type,selection,answer,score,grade) values " +
                "(?,?,?,?,?,?,?,?,?,?)";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,JdbcUtil.getUUID(),
                aq.getAnswerid(),
                aq.getUserid(),
                aq.getNumber(),
                aq.getContent(),
                aq.getType(),
                aq.getSelection(),
                aq.getAnswer(),
                aq.getScore(),
                aq.getGrade());
    }
    //通过答卷id和用户id获取全部答卷题目
    public static List<AnswerQuestion> getAllAnswerQuestionByChoose(String userid,String answerid) throws SQLException {
        String sql = "select *from answer_questions where userid = ? and answerid = ? ";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        return qr.query(sql,new BeanListHandler<AnswerQuestion>(AnswerQuestion.class),userid,answerid);
    }

    public static void updateUserAnswerQuestion(String uanswer,String id) throws SQLException {
        String sql = "update answer_questions set uanswer = ? where id = ?";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,uanswer,id);
    }
}
