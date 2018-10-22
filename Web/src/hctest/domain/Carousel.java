package hctest.domain;

import hctest.util.PropertiesUitl;
import net.sf.json.JSONObject;

public class Carousel {
    private String id;
    private String url;
    private String number;
    private String title;
    private String content;
    private int open;

    public JSONObject toJson() {
        JSONObject jo = new JSONObject();
        jo.put("id",id);
        jo.put("url", url);
        jo.put("number",number);
        jo.put("title",title);
        jo.put("content",content);
        return jo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }
}
