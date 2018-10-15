package hctest.dto;

import hctest.domain.Paper;
import hctest.domain.Question;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.List;

public class PaperInfo {
    private String id;
    private String title;
    private String number;
    private String direction;
    private int grade;
    private int permit;
    private String createtime;
    private String updatetime;
    private String starttime;
    private String endtime;
    private long createtimeInt;
    private long updatetimeInt;
    private long starttimeInt;
    private long endtimeInt;

    public List<Question> getQuestionInfoList() {
        return questionInfoList;
    }

    public void setQuestionInfoList(List<Question> questionInfoList) {
        this.questionInfoList = questionInfoList;
    }

    private List<Question>questionInfoList;


    public Paper toPaper(){
        Paper paper = new Paper();
        paper.setId(id);
        paper.setNumber(number);
        paper.setPermit(permit);
        paper.setGrade(grade);
        paper.setTitle(title);
        paper.setDirection(direction);
        paper.setEndtime(new Date(endtimeInt));
        paper.setStarttime(new Date(starttimeInt));
        paper.setCreatetime(new Date(createtimeInt));
        paper.setUpdatetime(new Date(updatetimeInt));
        return paper;
    }

    public PaperInfo(Paper paper)
    {
        setId(paper.getId());
        setNumber(paper.getNumber());
        setPermit(paper.getPermit());
        setGrade(paper.getGrade());
        setTitle(paper.getTitle());
        setDirection(paper.getDirection());
        setEndtime(paper.getEndtime().toString());
        setStarttime(paper.getStarttime().toString());
        setCreatetime(paper.getCreatetime().toString());
        setUpdatetime(paper.getUpdatetime().toString());
        setStarttimeInt(paper.getStarttime().getTime());
        setEndtimeInt(paper.getEndtime().getTime());
    }

    public JSONObject toJson()
    {
        JSONObject jo = new JSONObject();
        jo.put("id",id);
        jo.put("title",title);
        jo.put("number",number);
        jo.put("direction",direction);
        jo.put("grade",grade);
        jo.put("permit",permit);
        jo.put("updatetime",updatetime);
        jo.put("createtime",createtime);
        jo.put("starttime",starttime);
        jo.put("endtime",endtime);
        jo.put("starttimeInt",starttimeInt);
        jo.put("endtimeInt",endtimeInt);

        JSONObject qlist = new JSONObject();
        int i = 0;
        for (Question question : questionInfoList)
        {
            qlist.put(String.valueOf(i++),QuestionInfo.toJson(question));
        }

        jo.put("questionlist",qlist);

        return jo;
    }

    public PaperInfo ()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getPermit() {
        return permit;
    }

    public void setPermit(int permit) {
        this.permit = permit;
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

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
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

    public long getStarttimeInt() {
        return starttimeInt;
    }

    public void setStarttimeInt(long starttimeInt) {
        this.starttimeInt = starttimeInt;
    }

    public long getEndtimeInt() {
        return endtimeInt;
    }

    public void setEndtimeInt(long endtimeInt) {
        this.endtimeInt = endtimeInt;
    }
}
