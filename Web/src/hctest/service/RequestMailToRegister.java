package hctest.service;

import hctest.Dao.UserDao;
import hctest.dto.VerifyCode;
import hctest.util.FileUitl;
import hctest.util.GraphicHelper;
import hctest.util.HeaderUitl;
import hctest.util.MailUtil;
import net.sf.json.JSONObject;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "RequestMailToRegister",urlPatterns = "/sendMail")
public class RequestMailToRegister extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        if(email==null)
        {
            jo.put("message","邮件发送失败");
            jo.put("status","600");
            jo.put("wait","-2");
        }
        long nowtime = new Date().getTime()/1000;
        Object mailtime = session.getAttribute("MailTimes");
        if((mailtime==null||nowtime-(long)mailtime>=60))
        {

            if(UserDao.isEmailExist(email))
            {
                jo.put("status","400");
                jo.put("message","邮箱已被使用");
            }
            else
            {
                VerifyCode vcode = GraphicHelper.randRomVerifyCode(180,40);
                String path = "temp/"+email+".jpeg";
                String realpath = getServletContext().getRealPath(path);
                OutputStream out = new FileOutputStream(realpath);
                ImageIO.write(vcode.getImage(),"jpeg",out);
                out.close();

                try {
                    String linkPath = getServletContext().getRealPath("registerVerify.html");
                    String content = FileUitl.getFileToString(linkPath);
                    content = content.replace("@@thereISsrc@@",vcode.getCode());
                    MailUtil.sendMail(email,"注册验证码，来自环创答题系统",content);
                    session.setAttribute("MailTimes",new Date().getTime()/1000);
                    session.setAttribute("MailCode",vcode.getCode());
                    session.setAttribute("MailAccount",email);
                    jo.put("status","200");
                    jo.put("message","邮件发送成功");
                    jo.put("wait","60");
                } catch (Exception e) {
                    jo.put("status","500");
                    jo.put("message","邮件发送失败");
                    jo.put("wait","-1");
                    e.printStackTrace();
                }
            }

        }
        else
        {
            jo.put("status","400");
            jo.put("message","邮件发送失败");
            jo.put("wait",60-(nowtime-(long)mailtime));
        }

        response.getWriter().write(jo.toString());
    }
}
