package hctest.dto;

import hctest.domain.User;

public class RegisterUser {
    private String username;
    private String password;
    private String email;
    private String motto;
    private String profession;
    private String college;
    private String code;

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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public User getUser()
    {
        User user =new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setMotto(motto);
        user.setCollege(college);
        user.setProfession(profession);
        return  user;
    }
}
