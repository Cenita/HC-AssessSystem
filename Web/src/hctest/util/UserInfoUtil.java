package hctest.util;

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
}
