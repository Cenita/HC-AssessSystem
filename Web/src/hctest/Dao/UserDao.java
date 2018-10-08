package hctest.Dao;

import hctest.domain.User;
import hctest.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
    public static User getUserById(String id) throws SQLException {
        String sql = "select *from user where id = ?";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        User user =  qr.query(sql , new BeanHandler<User>(User.class),id);

        return user;
    }

    public static User getUserByUserName(String username) throws SQLException {
        String sql = "select *from user where username = ?";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

       User user =  qr.query(sql , new BeanHandler<User>(User.class),username);

       return user;
    }

    public static User getUserByEmail(String email) throws SQLException {
        String sql = "select *from user where email = ?";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        User user =  qr.query(sql , new BeanHandler<User>(User.class),email);

        return user;
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

    public static List<User> getAllUsers() throws SQLException {
        String sql = "select * from user";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        return qr.query(sql, new BeanListHandler<User>(User.class));
    }

    public static boolean isEmailExist(String email)
    {
        try {
            User user = UserDao.getUserByEmail(email);
            if(user==null)
            {
                return false;
            }
            else
            {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void updateUserPassword(String password,String id) throws SQLException {
        String sql = "update user set password = ? where id = ?";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,password,id);
    }

}
