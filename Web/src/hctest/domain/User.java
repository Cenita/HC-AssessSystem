package hctest.domain;

import net.sf.json.JSONObject;

import java.util.Date;
import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String motto;
    private Date createtime;
    private Date updatetime;
    private int permit;
    private String college;
    private String profession;
    private int grade;
    private String truename;
    private String headimage;

    public JSONObject toJson()
    {
        JSONObject jo = new JSONObject();
        jo.put("id",id);
        jo.put("username",username);
        jo.put("email",email);
        jo.put("motto",motto);
        jo.put("createtime",createtime.toString());
        jo.put("updatetime",updatetime.toString());
        jo.put("updatetimeInt",updatetime.getTime());
        jo.put("permitInt",permit);
        jo.put("permit",User.getPremitMessage(permit));
        jo.put("college",college);
        jo.put("profession",profession);
        jo.put("grade",grade);
        jo.put("truename",truename);
        jo.put("headimage",headimage);
        return jo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public int getPermit() {
        return permit;
    }

    public void setPermit(int permit) {
        this.permit = permit;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

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
