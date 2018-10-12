package hctest.util;

import hctest.domain.User;
import net.sf.json.JSONObject;

public class UserInfoUtil {

    public static String getPremitMessage(int premit)
    {
        String ps = "";
        switch (premit)
        {
            case 0:ps="游客";break;
            case 1:ps="用户";break;
            case 2:ps="活跃用户";break;
            case 3:ps="考核成员";break;
            case 4:ps="正式成员";break;
            case 5:ps="管理员";break;
            case 6:ps="负责人";break;
            case 9:ps="超级管理员";break;
        }
        return ps;
    }

    public static JSONObject getUserMessageToJson(User user)
    {
        if(user==null)
        {
            return null;
        }
        else
        {
            JSONObject jo = new JSONObject();
            jo.put("username",user.getUsername());
            jo.put("lasttime",user.getUpdatetime().toString());
            jo.put("motto",user.getMotto());
            jo.put("id",user.getId());
            jo.put("profession",user.getProfession());
            jo.put("college",user.getCollege());
            jo.put("registertime",user.getCreatetime().toString());
            jo.put("grade",user.getGrade());
            jo.put("email",user.getEmail());
            jo.put("permit",UserInfoUtil.getPremitMessage(user.getPermit()));
            jo.put("permitInt",user.getPermit());
            jo.put("headImage",user.getHeadimage());
            return jo;
        }
    }
}
