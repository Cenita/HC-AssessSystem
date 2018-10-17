package hctest.domain;

import net.sf.json.JSONObject;

public class AnswerQuestion {
    private String id;
    private String answerid;
    private int number;
    private String content;
    private int type;
    private String selection;
    private String uanswer;
    private String answer;
    private int score;
    private int grade;
    private String userid;

    public JSONObject toJson()
    {
        JSONObject jo = new JSONObject();
        jo.put("id",id);
        jo.put("answerid",answerid);
        jo.put("number",number);
        jo.put("content",content);
        jo.put("type",type);
        jo.put("selection",selection);
        jo.put("uanswer",uanswer);
        jo.put("answer",answer);
        jo.put("score",score);
        jo.put("grade",grade);
        jo.put("userid",userid);
        return jo;
    }


    public String getUanswer() {
        return uanswer;
    }

    public void setUanswer(String uanswer) {
        this.uanswer = uanswer;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }




    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswerid() {
        return answerid;
    }

    public void setAnswerid(String answerid) {
        this.answerid = answerid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
