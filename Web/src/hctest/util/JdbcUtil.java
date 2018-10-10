package hctest.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.UUID;

public class JdbcUtil {

    public static DataSource ds = null;
    static {
        Properties ps = new Properties();
        String dbPath = JdbcUtil.class.getClassLoader().getResource("db.properties").getPath();
        FileInputStream in;
        try {
           in = new FileInputStream(dbPath);
           ps.load(in);
           ds = DruidDataSourceFactory.createDataSource(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static DataSource getDataSource(){
        return ds;
    }

    public static void print(){
        System.out.println("helloworld");
    }

    public static String getUUID()
    {
        return UUID.randomUUID().toString().replace("-","");
    }

}
