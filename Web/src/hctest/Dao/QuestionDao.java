package hctest.Dao;

import hctest.domain.Question;
import hctest.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

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


}
