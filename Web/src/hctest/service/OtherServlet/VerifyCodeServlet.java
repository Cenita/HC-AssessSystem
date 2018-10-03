package hctest.service.OtherServlet;

import hctest.dto.VerifyCode;
import hctest.util.GraphicHelper;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "VerifyCodeServlet",
        urlPatterns = "/verifyCode"
)
public class VerifyCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("验证码内容: " + vcode.getCode());
    }
}
