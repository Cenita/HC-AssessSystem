package hctest.dto;

import hctest.domain.Question;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.List;

public class QuestionInfo {
    private String id;
    private String number;
    private String title;
    private String content;
    private String direction;
    private int grade;
    private String answer;
    private int type;
    private Date createtime;
    private Date updatetime;

    public static JSONObject toJson(Question question)
    {
        JSONObject jo = new JSONObject();
        jo.put("id",question.getId());
        jo.put("title",question.getTitle());
        jo.put("number",question.getNumber());
        jo.put("content",question.getContent());
        jo.put("type",question.getType());
        jo.put("selection",question.getSelection());
        jo.put("answer",question.getAnswer());
        jo.put("direction",question.getDirection());
        jo.put("grade",question.getGrade());
        jo.put("createtime",question.getCreatetime().toString());
        jo.put("updatetime",question.getUpdatetime().toString());
        jo.put("createtimeInt",question.getCreatetime().getTime());
        jo.put("updatetimeInt",question.getUpdatetime().getTime());

        return jo;
    }
}
