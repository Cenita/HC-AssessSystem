package test;

import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.opm.PaperOpm;
import hctest.util.FileUitl;
import hctest.util.JdbcUtil;
import hctest.util.MailUtil;
import hctest.util.PropertiesUitl;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@WebServlet(name = "test",
        urlPatterns = "/test"
)
public class Test extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            PaperOpm.getPaperInfoByPaperId("06a8c02996474397abda52dc3a5f35d3");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
