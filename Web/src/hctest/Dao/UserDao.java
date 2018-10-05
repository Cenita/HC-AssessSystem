package hctest.Dao;

import hctest.domain.User;
import hctest.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class UserDao{
    public static User getUserById(String id) {
        return null;
    }

    public static User getUserByUserName(String username) throws SQLException {
        String sql = "select *from user where username = ?";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

       User user =  qr.query(sql , new BeanHandler<User>(User.class),username);

       return user;
    }

    public static User getUserByEmail(String email) {
        return null;
    }

    public static void addUser(User user) throws SQLException {
        String sql = "insert into user " +
                "(id,username,password,email,motto,college,profession,createtime,updatetime,grade,truename) " +
                "values (?,?,?,?,?,?,?,?,?,?,?)";
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        qr.update(sql,
                User.getRandonUUID(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getMotto(),
                user.getCollege(),
                user.getProfession(),
                timestamp,
                timestamp,
                user.getGrade(),
                user.getTruename());
    }

    public static void updateUser(User user) {

    }

    public static void updateUserUpdateTime(String username) throws SQLException {

        String sql = "update user set updatetime = ? where username = ?";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        Date date = new Date();

        qr.update(sql, new Timestamp(date.getTime()),username);
    }

    public static List<User> getAllUsers() {
        return null;
    }

}
