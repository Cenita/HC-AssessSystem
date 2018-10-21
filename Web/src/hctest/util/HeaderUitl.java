package hctest.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class HeaderUitl {

    public static String getIpuser(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {ip = request.getHeader("Proxy-Client-IP");}if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {ip = request.getHeader("WL-Proxy-Client-IP");}if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {ip = request.getRemoteAddr();}if (ip.equals("0:0:0:0:0:0:0:1")) {ip = "127.0.0.1";}return ip;
    }

    public static void setHeaderAccess(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Method", "POST,GET");
        response.setHeader("Access-Control-Allow-Credentials","true");
    }

}
