package hctest.dto;

import hctest.domain.Answer;
import hctest.domain.AnswerQuestion;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.List;

public class AnswerInfo {
    private String id;
    private String paperid;
    private String userid;
    private int status;
    private Date createtime;
    private Date updatetime;
    List<AnswerQuestion>answerQuestionList;

    public AnswerInfo(){}

    public AnswerInfo(Answer answer)
    {
        setId(answer.getId());
        setUserid(answer.getUserid());
        setCreatetime(answer.getCreatetime());
        setUpdatetime(answer.getUpdatetime());
        setStatus(answer.getStatus());
        setPaperid(answer.getPaperid());
    }

    public Answer toAnswer()
    {
        Answer answer = new Answer();
        answer.setId(id);
        answer.setUserid(userid);
        answer.setPaperid(paperid);
        answer.setStatus(status);
        answer.setCreatetime(createtime);
        answer.setUpdatetime(updatetime);
        return answer;
    }

    public JSONObject toJson()
    {
        JSONObject answerinfo = toAnswer().tojson();
        JSONObject aqList = new JSONObject();
        int i = 0;
        for(AnswerQuestion aq : answerQuestionList)
        {
            aqList.put(String.valueOf(i++),aq.toJson());
        }
        answerinfo.put("size",answerQuestionList.size());
        answerinfo.put("questionList",aqList);
        return  answerinfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public List<AnswerQuestion> getAnswerQuestionList() {
        return answerQuestionList;
    }

    public void setAnswerQuestionList(List<AnswerQuestion> answerQuestionList) {
        this.answerQuestionList = answerQuestionList;
    }
}
