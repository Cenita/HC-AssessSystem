package hctest.domain;

import net.sf.json.JSONObject;

import java.util.Date;

public class Answer {
    private String id;
    private String paperid;
    private String userid;
    private int status;
    private Date createtime;
    private Date updatetime;

    public JSONObject tojson()
    {
        JSONObject jo = new JSONObject();
        jo.put("id",id);
        jo.put("paperid",paperid);
        jo.put("userid",userid);
        jo.put("status",getStatusMean(status));
        jo.put("statusInt",status);
        jo.put("createtime",createtime.toString());
        jo.put("updatetime",updatetime.toString());
        return jo;
    }

    public static String getStatusMean(int status){
        switch (status)
        {
            case 0:return "未提交";
            case 1:return "待批改";
            case 2:return "被撤回";
            case 3:return "已批改";
        }
        return "未知";
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
}
