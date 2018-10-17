package hctest.verify;

import hctest.Dao.UserDao;
import hctest.base.ServiceBaseServlet;
import hctest.domain.User;
import hctest.dto.VerifyCode;
import hctest.util.*;
import net.sf.json.JSONObject;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "VerifyServlet",urlPatterns = "/verify")
public class VerifyServlet extends ServiceBaseServlet {
    public void sendMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        JSONObject jo = new JSONObject();
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        if(email==null) return;
        long nowtime = new Date().getTime()/1000;
        Object mailtime = session.getAttribute(Config.MailTimes);
        if((mailtime==null||nowtime-(long)mailtime>=60))
        {

            if(UserDao.isEmailExist(email))
            {
                jo.put(Config.Status,Status.Fail);
                jo.put(Config.Message,"邮箱已被使用");
            }
            else
            {
                VerifyCode vcode = GraphicHelper.randRomVerifyCode(180,40);
                String path = "fileSystem/public/temp/"+email+".jpeg";
                String realpath = getServletContext().getRealPath(path);
                OutputStream out = new FileOutputStream(realpath);
                ImageIO.write(vcode.getImage(),"jpeg",out);
                out.close();

                try {
                    String linkPath = getServletContext().getRealPath("registerVerify.html");
                    String content = FileUitl.getFileToString(linkPath);
                    content = content.replace("@@thereISsrc@@",vcode.getCode());
                    MailUtil.sendMail(email,"注册验证码，来自环创答题系统",content);
                    session.setAttribute(Config.MailTimes,new Date().getTime()/1000);
                    session.setAttribute(Config.MailCode,vcode.getCode());
                    session.setAttribute(Config.MailAccount,email);
                    jo.put(Config.Status,Status.Succeed);
                    jo.put(Config.Message,"邮件发送成功");
                    jo.put("wait","60");
                } catch (Exception e) {
                    jo.put(Config.Status,Status.Fail);
                    jo.put(Config.Message,"邮件发送失败");
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

    public void getCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        String p_width = request.getParameter("width");
        String p_height = request.getParameter("height");
        int width = 180;
        int height = 40;
        if(p_width!=null) width = Integer.valueOf(p_width);
        if(p_height!=null) height = Integer.valueOf(p_height);

        final String imgType = "jpeg"; // 指定图片格式 (不是指MIME类型)

        final OutputStream output = response.getOutputStream();

        //获得一个随机的验证码
        VerifyCode vcode = GraphicHelper.randRomVerifyCode(width,height);

        try {
            ImageIO.write(vcode.getImage(), imgType, output);
            session.setAttribute(Config.VerifyCode,vcode.getCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void username(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("application/json;charset=utf-8");
        JSONObject jo = new JSONObject();
        String username = request.getParameter("username");
        if(username==null) return;

        User user = UserDao.getUserByUserName(username);
        if(user==null){
            jo.put(Config.Status,Status.Succeed);
            jo.put(Config.Message,"可以使用");
        }
        else
        {
            jo.put(Config.Status,Status.Fail);
            jo.put(Config.Message,"已存在");
        }
        response.getWriter().write(jo.toString());
    }
}
