package hctest.Dao;

import hctest.domain.User;
import hctest.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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
                "(id,username,password,email,motto,college,profession) " +
                "values (?,?,?,?,?,?,?)";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        qr.update(sql,
                User.getRandonUUID(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getMotto(),
                user.getCollege(),
                user.getProfession());
    }

    public static void updateUser(User user) {

    }

    public static List<User> getAllUsers() {
        return null;
    }
}
