package hctest.service.filesystem;

import hctest.util.HeaderUitl;
import hctest.util.ReturnUtil;
import net.sf.json.JSONObject;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "uploadFileServlet",urlPatterns = "/fileSystem")
public class uploadFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        HeaderUitl.setHeaderAccess(request,response);

        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();

        if(!ServletFileUpload.isMultipartContent(request))
        {
            ReturnUtil.ToReturn("600","请设置正确的文件上传格式",response);return;
        }

        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());


        String savePath = getServletContext().getRealPath("/WEB-INF/upload");

        File file = new File(savePath);

        if(!file.exists())
        {
            file.mkdir();
        }

        try {
            List<FileItem> fileItems = upload.parseRequest(new ServletRequestContext(request));

            System.out.println(fileItems.size());

            for (FileItem item :fileItems)
            {

            }



        } catch (FileUploadException e) {
            e.printStackTrace();
        }


        jo.put("status","200");
        jo.put("message","文件上传成功");

        response.getWriter().write(jo.toString());
    }
}
