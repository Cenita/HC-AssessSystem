package hctest.opm;

import hctest.Dao.UserDao;
import hctest.domain.User;
import hctest.dto.VerifyCode;
import hctest.util.Config;
import hctest.util.FileUitl;
import hctest.util.GraphicHelper;
import hctest.util.MailUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class VerifyOpm {

    public static boolean sendMailToRegister(String email,ServletContext context,HttpSession session) throws Exception {

        if(UserDao.getUserByEmail(email)!=null)
        {
            return false;
        }

        VerifyCode vcode = GraphicHelper.randRomVerifyCode(180,40);
        String linkPath = context.getRealPath("view/registerVerify.html");
        String content = FileUitl.getFileToString(linkPath);
        content = content.replace("@@thereISsrc@@",vcode.getCode());
        MailUtil.sendMail(email,"注册验证码，来自环创答题系统",content);
        session.setAttribute(Config.MailCode,vcode.getCode().toLowerCase());
        session.setAttribute(Config.MailAccount,email);
        session.setAttribute(Config.MailTimes,new Date().getTime()/1000);
        return true;
    }
}
