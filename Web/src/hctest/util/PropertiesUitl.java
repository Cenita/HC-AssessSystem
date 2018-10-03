package hctest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertiesUitl {
    public static Properties getProperties(String path) throws Exception {

        String realPath = PropertiesUitl.class.getClassLoader().getResource(path).getPath();
        FileInputStream in = new FileInputStream(realPath);

        Properties p = new Properties();

        p.load(in);
        return p;
    }
}
