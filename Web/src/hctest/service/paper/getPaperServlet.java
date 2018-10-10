package hctest.service.paper;

import hctest.Dao.PaperDao;
import hctest.Dao.UserDao;
import hctest.domain.Paper;
import hctest.domain.User;
import hctest.dto.PaperInfo;
import hctest.opm.PaperOpm;
import hctest.util.HeaderUitl;
import hctest.util.ReturnUtil;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetPaperServlet",urlPatterns = "/paper/get")
public class getPaperServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();

        Object login = session.getAttribute("login");
        if(login==null) return;

        try {
            User admin = UserDao.getUserById((String)login);

            String aim = request.getParameter("aim");
            String args = request.getParameter("args");

            if(aim==null||args==null)
            {
                ReturnUtil.ToReturn("600","参数非法",response);return;
            }

            if(aim.equals("all"))
            {
                List<PaperInfo> paperInfoList = PaperOpm.getAllPaperWithInfo();
                jo.put("size",paperInfoList.size());
                jo.put("status","200");
                jo.put("message","获取成功");
                JSONObject paperlist = new JSONObject();
                int i=0;
                for(PaperInfo paperInfo:paperInfoList)
                {
                    paperlist.put(String.valueOf(i++),paperInfo.toJson());
                }
                jo.put("list",paperlist);

            }
            else if(aim.equals("id"))
            {
                PaperInfo paperInfo = PaperOpm.getPaperInfoByPaperId(args);
                if(paperInfo==null)
                {
                    jo.put("message","无法找到");
                    jo.put("status","400");
                }
                else
                {
                    jo.put("paper",paperInfo.toJson());
                    jo.put("message","查找成功");
                    jo.put("status","200");
                }


            }
            else
            {
                return;
            }



        } catch (SQLException e) {
            e.printStackTrace();
            jo.put("status","500");
            jo.put("message","发生sql错误");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("sattus","600");
            jo.put("message","参数非法");
        }


        response.getWriter().write(jo.toString());
    }
}
