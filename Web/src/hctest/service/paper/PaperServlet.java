package hctest.service.paper;

import hctest.Dao.PaperDao;
import hctest.base.LoginBaseServlet;
import hctest.domain.Paper;
import hctest.dto.PaperInfo;
import hctest.opm.PaperOpm;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "PaperServlet",urlPatterns = "/paper/v2")
public class PaperServlet extends LoginBaseServlet {
    public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject) request.getAttribute("jo");

        String id = request.getParameter("id");
        if(id==null)return;

        PaperInfo paperInfo = PaperOpm.getPaperInfoByPaperId(id);

        jo.put("paper",paperInfo.toJson());
        jo.put("status","200");
        jo.put("message","获取成功");
    }
    //获取原始paper全部数据
    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject) request.getAttribute("jo");

        List<Paper> paperList = PaperDao.getAllPaper();

        jo.put("size",paperList.size());
        JSONObject list = new JSONObject();
        int i = 0;
        for (Paper paper:paperList)
        {
            list.put(String.valueOf(i++),PaperOpm.PaperToJson(paper));
        }
        jo.put("paperList",list);
        jo.put("status","200");
        jo.put("message","获取成功");
    }

    //删除某张试卷
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        JSONObject jo = (JSONObject) request.getAttribute("jo");
        String paperid = request.getParameter("request");
        if(paperid==null) return;
        PaperOpm.deletePaperWithAll(paperid);
        jo.put("status","200");
        jo.put("message","删除成功");
    }




}
