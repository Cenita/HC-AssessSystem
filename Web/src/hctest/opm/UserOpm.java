package hctest.opm;

import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.util.PropertiesUitl;
import org.apache.tomcat.util.http.fileupload.FileItem;

import javax.servlet.ServletContext;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.SQLException;

public class UserOpm {

    public static void updateHeadImage(User user, FileItem item, ServletContext context) throws Exception {
        String Name = item.getName();
        String suffixname = Name.substring(Name.lastIndexOf("."));
        String fileName = user.getId()+suffixname;

        String saveDir = context.getRealPath(PropertiesUitl.getHeadImagePath());
        String realurl = PropertiesUitl.getWebUrl()+PropertiesUitl.getHeadImagePath()+fileName;

        InputStream in = item.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(saveDir+fileName);

        byte buffer[] = new byte[1024];

        int len=0;

        while ((len = in.read(buffer))>0 )
        {
            fileOutputStream.write(buffer,0,len);
        }

        fileOutputStream.close();
        in.close();
        item.delete();

        UserDao.updateUserHeadImage(realurl,user.getId());

    }
}
