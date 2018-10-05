package hctest.domain;

import net.sf.json.JSONObject;

import java.util.Date;

public class Question {
    private String id;
    private String number;
    private String title;
    private String content;
    private String direction;
    private String selection;
    private String answer;
    private int grade;
    private int type;
    private Date createtime;
    private Date updatetime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
    public JSONObject toJSONObject()
    {
        JSONObject jo = new JSONObject();
        jo.put("id",id);
        jo.put("title",title);
        jo.put("number",number);
        jo.put("content",content);
        jo.put("type",type);
        jo.put("selection",selection);
        jo.put("answer",answer);
        jo.put("direction",direction);
        jo.put("grade",grade);
        jo.put("createtime",createtime.getTime());
        jo.put("updatetime",updatetime.getTime());

        return jo;
    }
}