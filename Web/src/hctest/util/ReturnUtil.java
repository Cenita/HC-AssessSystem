package hctest.util;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReturnUtil {

    public static void ToReturn(String status, String message, HttpServletResponse response) throws IOException {

        JSONObject jo = new JSONObject();

        jo.put("status",status);
        jo.put("message",message);

        response.getWriter().write(jo.toString());
    }
}
