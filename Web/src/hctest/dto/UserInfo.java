package hctest.dto;

import hctest.domain.User;

import java.util.Date;

public class UserInfo {
    private String id;
    private String username;
    private String password;
    private String email;
    private String motto;
    private String createtime;
    private String updatetime;
    private long createtimeInt;
    private long updatetimeInt;
    private String permit;
    private int permitInt;
    private String college;
    private String profession;
    private int grade;
    private int gradeInt;

    public UserInfo(){}
    public UserInfo(User user){
        setId(user.getId());
        setUsername(user.getUsername());
        setPassword(user.getPassword());
        setEmail(user.getEmail());
        setMotto(user.getMotto());
        setCreatetime(user.getCreatetime().toString());
        setUpdatetime(user.getUsername().toString());
        setCreatetimeInt(user.getCreatetime().getTime());
        setUpdatetimeInt(user.getUpdatetime().getTime());
        setPermitInt(user.getPermit());
        setPermit(UserInfo.getPremitMessage(user.getPermit()));
        setCollege(user.getCollege());
        setProfession(user.getProfession());
        setGrade(user.getGrade());
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public long getCreatetimeInt() {
        return createtimeInt;
    }

    public void setCreatetimeInt(long createtimeInt) {
        this.createtimeInt = createtimeInt;
    }

    public long getUpdatetimeInt() {
        return updatetimeInt;
    }

    public void setUpdatetimeInt(long updatetimeInt) {
        this.updatetimeInt = updatetimeInt;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public int getPermitInt() {
        return permitInt;
    }

    public void setPermitInt(int permitInt) {
        this.permitInt = permitInt;
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

    public int getGradeInt() {
        return gradeInt;
    }

    public void setGradeInt(int gradeInt) {
        this.gradeInt = gradeInt;
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
            default:ps="未知";
        }
        return ps;
    }

}
