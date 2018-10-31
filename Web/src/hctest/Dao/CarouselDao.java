package hctest.Dao;

import hctest.domain.Carousel;
import hctest.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CarouselDao {
    public static void add(Carousel carousel) throws SQLException {
        String sql = "insert into carousel (id,url,title,number,content,open) values " +
                "(?,?,?,?,?,?) ";
        String number = String.valueOf(new Date().getTime());
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,carousel.getId(),carousel.getUrl(),carousel.getTitle(),number,carousel.getContent(),0);
    }

    public static void update(Carousel carousel) throws SQLException {
        String sql = "update carousel " +
                "set title = ? ," +
                "url = ? ," +
                "number = ? ," +
                "content = ? ," +
                "open = ? where id = ? ";

        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

        qr.update(sql,carousel.getId(),
                carousel.getTitle(),
                carousel.getUrl(),
                carousel.getNumber(),
                carousel.getContent(),
                carousel.getOpen());
    }

    public static Carousel getCarouselById(String id) throws SQLException {
        String sql = "select * from carousel where id = ?";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        return  qr.query(sql,new BeanHandler<Carousel>(Carousel.class),id);
    }

    public static List<Carousel> getAllCarousel() throws SQLException {
        String sql = "select * from carousel";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        return  qr.query(sql,new BeanListHandler<Carousel>(Carousel.class));
    }

    public static List<Carousel> getListOpenCarousel() throws SQLException {
        String sql = "select * from carousel where open = 1";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        return  qr.query(sql,new BeanListHandler<Carousel>(Carousel.class));
    }

    public static void delete(String id) throws SQLException {
        String sql = "delete from carousel where id = ?";
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        qr.update(sql,id);
    }

}
